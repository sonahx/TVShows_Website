package com.TVShows.service;

import com.TVShows.DTO.RegisterRequest;
import com.TVShows.domain.ConfirmationToken;
import com.TVShows.domain.User;
import com.TVShows.enums.Role;
import com.TVShows.exceptions.EmailAlreadyTakenException;
import com.TVShows.exceptions.UsernameAlreadyTakenException;
import com.TVShows.exceptions.WrongCredentialsException;
import com.TVShows.mail.EmailBuilder;
import com.TVShows.mail.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ConfirmationTokenService tokenService;
    private final MailSender mailSender;
    @Value("${domain.url}")
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
            throw new WrongCredentialsException("Registration credentials doesn't meet the requirements");
        }

        // Check if user with requested username already exists
        if (userService.findByUsername(requestedUsername).isPresent()) {
            throw new UsernameAlreadyTakenException("Username is already taken");
        }

        // Check if user with requested email already exists
        if (userService.findByEmail(requestedEmail).isPresent()) {
            throw new EmailAlreadyTakenException("Email is already taken");
        }

        // Create new user
        log.info("Registering user {}, {}", requestedUsername, requestedEmail);
        User user = User.builder()
                .name(requestedUsername)
                .email(requestedEmail)
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.USER)
                .build();
        userService.saveUser(user);

        // Create and send confirmation link
        ConfirmationToken token = tokenService.generateToken(user);
        String link = url + "/auth/confirm?token=" + token.getToken();
        mailSender.sendEmail(
                requestedEmail,
                "confirm your email",
                EmailBuilder.build(requestedUsername, link)
                );
    }

    public void forgotPassword (String email){
        Optional<User> user = userService.findByEmail(email);
        if(user.isPresent()){
            ConfirmationToken token = tokenService.generateToken(user.get());
            String link = url + "/auth/passwordReset?token=" + token.getToken();
            mailSender.sendEmail(
                    email,
                    "password reset",
                    EmailBuilder.forgotPassword(user.get().getName(), link)
            );
            log.info("Password reset link has been sent to {}", email);
        }
        else {
            log.info("User with email: {} doesn't exist", email);
        }
    }
}
