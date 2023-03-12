package com.TVShows.repo;

import java.util.Optional;

import com.TVShows.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVShowRepo extends JpaRepository<TVShow, Long>{
	
	Optional<TVShow> findShowByName(String name);
	
}
