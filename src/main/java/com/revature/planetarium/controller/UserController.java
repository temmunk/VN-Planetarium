package com.revature.planetarium.controller;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.AuthenticationFailed;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.service.user.UserService;
import java.util.Map;

import io.javalin.http.Context;


public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            userService.createUser(user);
            ctx.status(201);
            ctx.json(Map.of("message","User created successfully"));
        } catch (UserFail e) {
            ctx.status(400);


            if (e.getMessage().contains("password")){
                ctx.json(Map.of("message","Invalid password"));
            } else {
                ctx.json(Map.of("message","Invalid username"));
            }
        }
    }

    public void login(Context ctx){
        User credentials = ctx.bodyAsClass(User.class);
        User user;
        try {
            user = userService.authenticate(credentials);
            ctx.sessionAttribute("user", user.getUsername());
            ctx.status(200);
            ctx.json(Map.of(
                    "id", user.getId(),
                    "username", user.getUsername()
            ));
        } catch (UserFail e) {
            ctx.status(401);
            ctx.json(Map.of("message", "invalid credentials"));

        }
    }


    public void logout(Context ctx){
        ctx.req().getSession().invalidate();
        ctx.status(200);
    }

    public void authenticateUser(Context ctx){
        if(ctx.req().getSession(false) == null){
            throw new AuthenticationFailed("Please log in first");
        }
    }
}
