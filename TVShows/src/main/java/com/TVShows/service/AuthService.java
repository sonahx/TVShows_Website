package com.TVShows.service;

import com.TVShows.DTO.EmailBuilder;
import com.TVShows.DTO.RegisterRequest;
import com.TVShows.domain.ConfirmationToken;
import com.TVShows.repo.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TVShows.domain.Role;
import com.TVShows.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ConfirmationTokenService tokenService;
    private final EmailSender emailSender;
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);

    public void register(RegisterRequest request) {
        String requestedUsername = request.getUsername();
        String requestedEmail = request.getEmail();

        // Check if user with requested username already exists
        if (userService.findByUsername(requestedUsername).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Check if user with requested email already exists
        if (userService.findByEmail(requestedEmail).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        // Create new user
        logger.info("Registering user {}, {}", requestedUsername, requestedEmail);
        User user = User.builder()
                .name(requestedUsername)
                .email(requestedEmail)
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.USER)
                .build();
        userService.createUser(user);

        // Create and send confirmation link
        ConfirmationToken token = tokenService.generateToken(user);
        String link = "http://localhost:8080/auth/confirm?token=" + token.getToken();
        emailSender.send(requestedEmail, EmailBuilder.build(requestedUsername, link));
    }
}
