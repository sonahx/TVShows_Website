package com.TVShows.service;

import com.TVShows.domain.ConfirmationToken;
import com.TVShows.domain.User;
import com.TVShows.repo.ConfirmationTokenRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConfirmationTokenTest {

    @Mock
    private ConfirmationTokenRepo tokenRepo;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConfirmationTokenService confirmationTokenService;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Test
    @DisplayName("Should successfully validate valid token")
    void shouldSuccessfullyValidateToken() {
        // given
        User user = User.builder().email("test@test.com").build();
        ConfirmationToken confirmationToken = new ConfirmationToken("valid_token",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                user);

        when(confirmationTokenService.findByToken("valid_token"))
                .thenReturn(Optional.of(confirmationToken));

        // when
        confirmationTokenService.confirmToken("valid_token");

        // then
        verify(userService).enableUser(stringCaptor.capture());
        assertEquals("test@test.com", stringCaptor.getValue());
    }

    @Test
    @DisplayName("Should throw exception for expired token")
    void shouldThrowExceptionForExpiredToken() {
        // given
        User user = User.builder().email("test@test.com").build();
        ConfirmationToken confirmationToken = new ConfirmationToken("expired_token",
                LocalDateTime.now(),
                LocalDateTime.now().minusDays(1),
                user);

        when(confirmationTokenService.findByToken("expired_token"))
                .thenReturn(Optional.of(confirmationToken));

        // when and then
        assertThrows(IllegalStateException.class,
                () -> confirmationTokenService.confirmToken("expired_token"));
    }

    @Test
    @DisplayName("Should throw exception for already confirmed token")
    void shouldThrowExceptionForAlreadyConfirmedToken() {
        // given
        User user = User.builder().email("test@test.com").build();
        ConfirmationToken confirmationToken = new ConfirmationToken("confirmed_token",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                user);
        confirmationToken.setConfirmedAt(LocalDateTime.now());

        when(confirmationTokenService.findByToken("confirmed_token"))
                .thenReturn(Optional.of(confirmationToken));

        // when and then
        assertThrows(IllegalStateException.class,
                () -> confirmationTokenService.confirmToken("confirmed_token"));
    }

    @Test
    @DisplayName("Should throw exception for invalid token")
    void shouldThrowExceptionForInvalidToken() {
        // given
        when(confirmationTokenService.findByToken("invalid_token"))
                .thenReturn(Optional.empty());

        // when and then
        assertThrows(IllegalStateException.class,
                () -> confirmationTokenService.confirmToken("invalid_token"));
    }
}
