package com.revature.planetarium.service.user;

import com.revature.planetarium.entities.User;

import java.util.Optional;

public interface UserService {
    
    public String createUser(User newUser);
    public User authenticate(User credentials);

    Optional<User> findUserByUsername(String username);
}
