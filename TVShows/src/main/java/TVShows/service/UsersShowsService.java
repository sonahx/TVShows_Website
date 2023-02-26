package TVShows.service;

import java.util.List;

import org.springframework.stereotype.Service;

import TVShows.domain.UsersShows;
import TVShows.repo.UsersShowsRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersShowsService {

	private final UsersShowsRepo repo;
	
	public void save(UsersShows usersShows) {
		repo.save(usersShows);
	}
	
	public List<UsersShows> findAll() {
		return repo.findAll();
	}
}
