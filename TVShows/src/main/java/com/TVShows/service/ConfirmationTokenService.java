package com.TVShows.service;

import com.TVShows.domain.ConfirmationToken;
import com.TVShows.domain.User;
import com.TVShows.repo.ConfirmationTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo repo;
    private final UserService userService;

    public ConfirmationToken save(ConfirmationToken confirmation){
        return repo.save(confirmation);
    }

    public Optional<ConfirmationToken> findByToken(String token){
        return repo.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return repo.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
    }

    public ConfirmationToken generateToken(User user){
        ConfirmationToken token = new ConfirmationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setIssuedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);
        return save(token);
    }
}
