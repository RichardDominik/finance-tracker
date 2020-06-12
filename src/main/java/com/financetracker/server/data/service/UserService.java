package com.financetracker.server.data.service;

import com.financetracker.server.data.entity.User;
import com.financetracker.server.data.exception.UserException;
import com.financetracker.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final String userNotFoundError = "Not found";
    private static final String userAlreadyExistsError = "User already exists";
    private static final String securityContextHolderError = "SecurityContextHolder does not found principal";

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository userRepository;

    public User loadUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(userNotFoundError + " " + email ));
    }

    public String getPrincipalEmail() {
        String email = "";
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            email = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
        } catch (Exception e){
            LOGGER.error(securityContextHolderError + " " + e.getMessage());
        }
        return email;
    }

    public void registerUser(User user) throws UserException {
        if(this.userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserException(userAlreadyExistsError);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
