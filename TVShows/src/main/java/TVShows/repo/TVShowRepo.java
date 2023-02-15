package TVShows.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TVShows.domain.TVShow;

@Repository
public interface TVShowRepo extends JpaRepository<TVShow, Long>{
	
	Optional<TVShow> findShowByName(String name);
	
}
