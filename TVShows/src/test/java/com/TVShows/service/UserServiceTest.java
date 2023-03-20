package com.TVShows.service;

import com.TVShows.domain.User;
import com.TVShows.repo.UserRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    @DisplayName("Test finding user with id")
    public void shouldFindUserById() {
        // given
        Long userId = 1L;
        User user = User.builder().id(userId).name("JohnDoe").email("johndoe@example.com").password("pass").build();

        // when
        when(userRepo.getReferenceById(userId)).thenReturn(user);
        User result = userService.findUserById(userId);
        assertEquals(user, result);

        // then
        verify(userRepo).getReferenceById(userId);
    }

    @Test
    @DisplayName("Test finding user by email")
    public void shouldFindByEmail() {
        // given
        String email = "johndoe@example.com";
        User user = User.builder().id(1L).name("JohnDoe").email(email).password("pass").build();

        // when
        when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmail(email);
        assertEquals(Optional.of(user), result);

        // then
        verify(userRepo).findByEmail(email);
    }

    @Test
    @DisplayName("Test creating user")
    public void shouldCreateUser() {
        // given
        User user = User.builder().id(1L).name("JohnDoe").email("johndoe@example.com").password("pass").build();

        // when
        userService.createUser(user);

        // then
        verify(userRepo).save(userCaptor.capture());
        assertEquals(user, userCaptor.getValue());
    }

    @Test
    @DisplayName("Test updating user")
    public void shouldUpdateUser() {
        // given
        User user = User.builder().id(1L).name("JohnDoe").email("johndoe@example.com").password("newpass").build();

        // when
        userService.updateUser(user);

        // then
        verify(userRepo).save(userCaptor.capture());
        assertEquals(user, userCaptor.getValue());
    }

    @Test
    @DisplayName("Test removing user")
    public void shouldRemoveUser() {
        // given
        User user = User.builder().id(1L).name("JohnDoe").email("johndoe@example.com").password("pass").build();

        // when
        userService.removeUser(user);

        // then
        verify(userRepo).delete(userCaptor.capture());
        assertEquals(user, userCaptor.getValue());
    }
}
