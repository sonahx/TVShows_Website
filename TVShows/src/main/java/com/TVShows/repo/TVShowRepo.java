package com.TVShows.repo;

import com.TVShows.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TVShowRepo extends JpaRepository<TVShow, Long>{
	
	Optional<TVShow> findShowByName(String name);
	
}
