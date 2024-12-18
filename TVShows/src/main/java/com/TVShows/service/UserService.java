package com.TVShows.service;

import com.TVShows.domain.User;
import com.TVShows.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User findUserById(int id) {
        log.info("Looking for user by id: {}", id);
        return userRepo.getReferenceById(id);
    }

    public Optional<User> findByEmail(String email) {
        log.info("Looking for user by email: {}", email);
        return userRepo.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        log.info("Looking for user by username: {}", username);
        return userRepo.findByName(username);
    }

    public void saveUser(User user) {
        log.info("Saving new user: {}", user.getName());
        userRepo.save(user);
    }

    public void updateUser(User user) {
        log.info("Updating user: {}, email: {}, id: {}", user.getName(), user.getEmail(), user.getId());
        userRepo.save(user);
    }

    public void removeUser(User user) {
        log.info("Removing user: {}, email: {}, id: {}", user.getName(), user.getEmail(), user.getId());
        userRepo.delete(user);
    }

    public void enableUser(String email) {
        log.info("Enabling user with email: {}", email);
        userRepo.enableUser(email);
    }
}
