package TVShows.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TVShows.DTO.RegisterRequest;
import TVShows.domain.Role;
import TVShows.domain.User;
import TVShows.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	 private final PasswordEncoder passwordEncoder;
	 private final UserRepo repository;

	  public void register(RegisterRequest request) {
	    var user = User.builder()
	        .username(request.getUsername())
	        .email(request.getEmail())
	        .password(passwordEncoder.encode(request.getPassword()))
	        .roles(Role.USER)
	        .build();
	    repository.save(user);
	  }
}
