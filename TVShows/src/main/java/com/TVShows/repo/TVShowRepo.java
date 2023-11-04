package com.TVShows.repo;

import com.TVShows.domain.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TVShowRepo extends JpaRepository<TvShow, Long>{
	
	Optional<TvShow> findShowByName(String name);

	@Query(value = "SELECT DISTINCT s.* FROM TVShow s WHERE s.name LIKE %:keyword% LIMIT 6", nativeQuery = true)
	List<TvShow> findShowsByKeyword(@Param("keyword") String keyword);
}
