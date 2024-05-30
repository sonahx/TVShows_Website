package com.TVShows.repo;

import com.TVShows.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TVShowRepo extends JpaRepository<TVShow, Long>{
	
	Optional<TVShow> findShowByName(String name);

	@Query(value = "SELECT DISTINCT s.* FROM tvshow s WHERE s.name LIKE %:keyword% LIMIT 6", nativeQuery = true)
	List<TVShow> findShowsByKeyword(@Param("keyword") String keyword);
}
