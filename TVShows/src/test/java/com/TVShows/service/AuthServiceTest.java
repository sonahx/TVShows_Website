package com.TVShows.service;

import com.TVShows.DTO.RegisterRequest;
import com.TVShows.domain.Role;
import com.TVShows.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthService authService;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    @DisplayName("Test successful registration")
    void shouldRegisterSuccessfully() {
        // Given
        RegisterRequest request = new RegisterRequest("johndoe", "johndoe@example.com", "password");

        // When
        when(userService.findByUsername(request.getUsername())).thenReturn(Optional.empty());
        when(userService.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        authService.register(request);

        // Then
        verify(userService).createUser(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertNotNull(savedUser);
        assertEquals(request.getUsername(), savedUser.getProfileUsername());
        assertEquals(request.getEmail(), savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(Role.USER, savedUser.getRoles());
    }

    @Test
    @DisplayName("Test registration with existing username")
    void shouldNotRegisterWithExistingUsername() {
        // Given
        RegisterRequest request = new RegisterRequest("johndoe", "johndoe@example.com", "password");


        // When/Then
        when(userService.findByUsername(request.getUsername())).thenReturn(Optional.of(new User()));
        assertThrows(IllegalArgumentException.class, () -> authService.register(request));

        // Verify that no user was created
        verify(userService, never()).createUser(any());
    }

    @Test
    @DisplayName("Test registration with existing email")
    void shouldNotRegisterWithExistingEmail() {
        // Given
        RegisterRequest request = new RegisterRequest("johndoe", "johndoe@example.com", "password");


        // When/Then
        when(userService.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));
        assertThrows(IllegalArgumentException.class, () -> authService.register(request));

        // Verify that no user was created
        verify(userService, never()).createUser(any());
    }

}
