package com.TVShows.repo;

import com.TVShows.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonProgressRepo extends JpaRepository<SeasonProgress, Long> {

   Optional<SeasonProgress> findByUsersShowProgressAndSeason(UsersShowProgress showProgress, Season season);

   @Query("SELECT sp FROM SeasonProgress sp " +
           "JOIN sp.usersShowProgress usp " +
           "JOIN usp.tvShow show " +
           "WHERE show = :tvShow " +
           "AND usp.user = :user")
   List<SeasonProgress> findSeasonProgressForShowAndUser(TVShow tvShow, User user);
}
