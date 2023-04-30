package com.TVShows.service;

import com.TVShows.domain.User;
import com.TVShows.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public User findUserById(Long id) {
        return userRepo.getReferenceById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        logger.info("Looking for user with username: {}", username);
        return userRepo.findByName(username);
    }

    public void createUser(User user) {
        userRepo.save(user);
    }

    public void updateUser(User user) {
        logger.info("Updating user: {}, email: {}, id: {}", user.getName(), user.getEmail(), user.getId());
        userRepo.save(user);
    }

    public void removeUser(User user) {
        logger.info("Removing user: {}, email: {}, id: {}", user.getName(), user.getEmail(), user.getId());
        userRepo.delete(user);
    }

    public void enableUser(String email){
        logger.info("Enabling user with email: {}", email);
        userRepo.enableUser(email);
    }
}
