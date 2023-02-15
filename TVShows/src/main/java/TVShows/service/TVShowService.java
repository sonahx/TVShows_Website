package TVShows.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TVShows.domain.TVShow;
import TVShows.repo.TVShowRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TVShowService {

	private final TVShowRepo showRepo;
	
	public void createShow(TVShow show) {
		showRepo.save(show);
	}
	
	public TVShow findShowById(Long id) {
	return	showRepo.getReferenceById(id);
	}
	
	public Optional<TVShow> findShowByName(String name) {
	return	showRepo.findShowByName(name);
	}
	
	public List<TVShow> findAllShows() {
	return	showRepo.findAll();
	}
	
	public void updateShow(TVShow show) {
		showRepo.save(show);
	}
	public void removeShow(TVShow show) {
		showRepo.delete(show);
	}
}
