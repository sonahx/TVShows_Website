package TVShows.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TVShows.DTO.AuthRequest;
import TVShows.DTO.AuthResponse;
import TVShows.DTO.RegisterRequest;
import TVShows.domain.Role;
import TVShows.domain.User;
import TVShows.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
		  private final UserRepo repository;
		  private final PasswordEncoder passwordEncoder;
		  private final JwtService jwtService;
		  private final AuthenticationManager authenticationManager;

		  public AuthResponse register(RegisterRequest request) {
		    var user = User.builder()
		        .username(request.getUsername())
		        .email(request.getEmail())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .role(Role.USER)
		        .build();
		    repository.save(user);
		    var jwtToken = jwtService.generateToken(user);
		    return AuthResponse.builder()
		        .token(jwtToken)
		        .build();
		  }

		  public AuthResponse authenticate(AuthRequest request) {
		    authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		            request.getEmail(),
		            request.getPassword()
		        )
		    );
		    var user = repository.findUserByEmail(request.getEmail())
		        .orElseThrow();
		    var jwtToken = jwtService.generateToken(user);
		    System.out.println("logged in successfully " + user.getUsername() +" " + jwtToken);
		    return AuthResponse.builder()
		        .token(jwtToken)
		        .build();
		  }
}
