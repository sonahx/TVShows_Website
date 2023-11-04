package com.TVShows.service;

import com.TVShows.DTO.RegisterRequest;
import com.TVShows.domain.ConfirmationToken;
import com.TVShows.domain.User;
import com.TVShows.enums.Role;
import com.TVShows.mail.EmailBuilder;
import com.TVShows.mail.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ConfirmationTokenService tokenService;
    @Mock
    private MailSender mailSenderService;
    @InjectMocks
    private AuthService authService;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    private RegisterRequest registerRequest;

    @Value("${domain.test.url}")
    private String url;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest("johndoe", "johndoe@example.com", "password");
    }

    @Test
    @DisplayName("Test successful registration")
    void shouldRegisterSuccessfully() {
        // Given
        RegisterRequest request = new RegisterRequest("johndoe", "johndoe@example.com", "password");
        User user = User.builder().name(request.getUsername()).email(request.getEmail()).password(request.getPassword()).build();
        ConfirmationToken token = new ConfirmationToken("token", LocalDateTime.now(), LocalDateTime.now(), user);

        when(userService.findByUsername(request.getUsername())).thenReturn(Optional.empty());
        when(userService.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");

        when(tokenService.generateToken(any())).thenReturn(token);
        assertNotNull(token);
        assertNotNull(token.getToken());
        // When
        authService.register(request);

        // Then
        verify(userService).createUser(userCaptor.capture());
        User createdUser = userCaptor.getValue();
        assertEquals(request.getUsername(), createdUser.getName());
        assertEquals(request.getEmail(), createdUser.getEmail());
        assertEquals("encoded_password", createdUser.getPassword());
        assertEquals(Role.USER, createdUser.getRoles());
        verify(tokenService).generateToken(createdUser);
        verify(mailSenderService).sendEmail(
                request.getEmail(),
                "confirm your email",
                EmailBuilder.build(request.getUsername(), url + "/auth/confirm?token=token")
                );
    }

    @Test
    @DisplayName("Test registration with existing username")
    void shouldNotRegisterWithExistingUsername() {
        // Given
        when(userService.findByUsername(registerRequest.getUsername())).thenReturn(Optional.of(new User()));

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> authService.register(registerRequest));

        // Verify that no user was created
        verify(userService, never()).createUser(any());
        verify(tokenService, never()).save(any());
        verify(mailSenderService, never()).sendEmail(any(), any(),any());
    }

    @Test
    @DisplayName("Test registration with existing email")
    void shouldNotRegisterWithExistingEmail() {
        // Given
        when(userService.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(new User()));

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> authService.register(registerRequest));

        // Verify that no user was created
        verify(userService, never()).createUser(any());
        verify(tokenService, never()).save(any());
        verify(mailSenderService, never()).sendEmail(any(), any(),any());
    }
}
