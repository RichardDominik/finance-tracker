package com.financetracker.server.controller;

import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.exception.UserException;
import com.financetracker.server.data.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            userService.registerUser(user);
        } catch (UserException e){
            LOGGER.error("Sign up failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Creating user succesfully", HttpStatus.BAD_REQUEST);
    }

}
