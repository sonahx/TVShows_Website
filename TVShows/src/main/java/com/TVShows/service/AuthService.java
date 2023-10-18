package com.TVShows.service;

import com.TVShows.DTO.EmailBuilder;
import com.TVShows.DTO.RegisterRequest;
import com.TVShows.domain.ConfirmationToken;
import com.TVShows.domain.User;
import com.TVShows.enums.Role;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ConfirmationTokenService tokenService;
    private final MailSenderService mailSenderService;
    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Value("${domain.test.url}")
    private String url;

    public void register(RegisterRequest request) {
        String requestedUsername = request.getUsername();
        String requestedEmail = request.getEmail();
        String requestedPassword = request.getPassword();

        if (requestedUsername.length() < 6
                || requestedUsername.length() > 20
                || requestedEmail.length() < 6
                || !requestedEmail.contains("@")
                || requestedPassword.length() < 6) {
            throw new IllegalArgumentException("Registration credentials doesn't meet the requirements");
        }

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
        String link = url + "/auth/confirm?token=" + token.getToken();
//        emailSender.send(requestedEmail, EmailBuilder.build(requestedUsername, link));
        mailSenderService.sendEmail(
                requestedEmail,
                "confirm your email",
                EmailBuilder.build(requestedUsername, link)
                );
    }
}
