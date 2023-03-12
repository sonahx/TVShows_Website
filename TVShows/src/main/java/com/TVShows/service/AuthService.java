package com.TVShows.service;

import com.TVShows.DTO.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TVShows.domain.Role;
import com.TVShows.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);

    public void register(RegisterRequest request) {
        String requestedUsername = request.getUsername();
        String requestedEmail = request.getEmail();

        // Check if user with requested username already exists
        if (userService.findByUsername(requestedUsername).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if user with requested email already exists
        if (userService.findByEmail(requestedEmail).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        logger.info("Registering user {}, {}", requestedUsername, requestedEmail);
        var user = User.builder()
                .username(requestedUsername)
                .email(requestedEmail)
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.USER)
                .build();
        userService.createUser(user);
    }
}
