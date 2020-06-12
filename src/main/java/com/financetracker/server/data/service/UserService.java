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
    private static final String USER_NOT_FOUND_ERROR = "Not found";
    private static final String USER_ALREADY_EXISTS_ERROR = "User already exists";
    private static final String SECURITY_CONTEXT_HOLDER_ERROR = "SecurityContextHolder does not found principal";

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository userRepository;

    public User loadUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR + " " + email ));
    }

    public String getPrincipalEmail() {
        String email = "";
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            email = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
        } catch (Exception e){
            LOGGER.error(SECURITY_CONTEXT_HOLDER_ERROR + " " + e.getMessage());
        }
        return email;
    }

    public void registerUser(User user) throws UserException {
        if(this.userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserException(USER_ALREADY_EXISTS_ERROR);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
