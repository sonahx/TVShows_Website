package com.TVShows.repo;

import com.TVShows.domain.Season;
import com.TVShows.domain.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepo extends JpaRepository<Season, Long> {

    @Query("SELECT s FROM Season s WHERE s.tvShow.id = :showId")
    List<Season> findAllSeasonsByShowId(Long showId);

     Optional<Season> findSeasonById(Long id);
}
