package com.revature.planetarium.service.user;


import java.util.List;
import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;

public class UserServiceImp implements UserService {
    
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String createUser(User newUser) {
        checkUsername(newUser.getUsername());
        checkPassword(newUser.getPassword());

        Optional<User> createdUser = userDao.createUser(newUser);
        if (createdUser.isPresent()) {
            return "User created successfully";
        }

        return "";
    }

    @Override
    public User authenticate(User credentials) {
        Optional<User> foundUser = userDao.findUserByUsername(credentials.getUsername());
        if (foundUser.isPresent()) {
            if (foundUser.get().getPassword().equals(credentials.getPassword())) {
                foundUser.get().setPassword(null);
                return foundUser.get();
            }
        }
        throw new UserFail("Invalid credentials");
    }

    private void checkUsername(String username) {
        if (username.length() > 30 || username.length() < 5 || !Character.isLetter(username.charAt(0))
                || userDao.findUserByUsername(username).isPresent()) {
            throw new UserFail("Invalid username");
        }

        for (int i = 0; i < username.length(); i++) {
            char usernameChar = username.charAt(i);
            if (!Character.isLetterOrDigit(usernameChar) && usernameChar != '-' && usernameChar != '_') {
                throw new UserFail("Invalid username");
            }
        }
    }

    private void checkPassword(String password) {
        if (password.length() > 30 || password.length() < 5 || !Character.isLetter(password.charAt(0))) {
            throw new UserFail("Invalid password");
        }

        boolean lowercaseFound = false, uppercaseFound = false, digitFound = false;
        for (int i = 0; i < password.length(); i++) {
            char passwordChar = password.charAt(i);
            if (!Character.isLetterOrDigit(passwordChar) && passwordChar != '-' && passwordChar != '_') {
                throw new UserFail("Invalid password");
            }

            if (Character.isLowerCase(passwordChar)) lowercaseFound = true;
            if (Character.isUpperCase(passwordChar)) uppercaseFound = true;
            if (Character.isDigit(passwordChar)) digitFound = true;
        }

        if (!(lowercaseFound && uppercaseFound && digitFound)) throw new UserFail("Invalid password");
    }

}
