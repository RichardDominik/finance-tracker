package com.financetracker.server.controller;

import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.exception.UserException;
import com.financetracker.server.data.service.UserService;
import com.financetracker.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final String userCreateSuccessfulMessage = "Creating user successful";
    private static final String userCreateError = "Creating user failed";
    private static final String userSignUpFailedError = "Sign up failed";

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
        try {
            userService.registerUser(user);
            return new ResponseEntity<>(userCreateSuccessfulMessage, HttpStatus.OK);
        } catch (UserException e){
            LOGGER.error(userSignUpFailedError + " " + e.getMessage());
        }
        return new ResponseEntity<>(userCreateError, HttpStatus.BAD_REQUEST);
    }
}
