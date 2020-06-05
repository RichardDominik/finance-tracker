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

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
        try {
            userService.registerUser(user);
            return new ResponseEntity<>("Creating user successful", HttpStatus.OK);
        } catch (UserException e){
            LOGGER.error("Sign up failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Creating user failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getStatistics() {
        try {
            return new ResponseEntity<>(userRepository.getStatistics(), HttpStatus.OK);
        } catch (UserException e){
            LOGGER.error("Statistics failed, error : " + e.getMessage());
        }
        return new ResponseEntity<>("Statistics failed", HttpStatus.BAD_REQUEST);
    }
}
