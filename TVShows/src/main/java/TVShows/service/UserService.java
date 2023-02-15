package TVShows.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import TVShows.domain.User;
import TVShows.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private UserRepo userRepo;

	public User getUserById(Long id) {
		return userRepo.getReferenceById(id);
	}
	
	public Optional<User> getUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}
	
	public void createUser(User user) {
		userRepo.save(user);
	}
	
	public void updateUser(User user) {
		userRepo.save(user);
	}
	
	public void removeUser(User user) {
		userRepo.delete(user);
	}
}
