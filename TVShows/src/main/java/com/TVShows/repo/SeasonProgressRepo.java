package com.TVShows.repo;

import com.TVShows.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonProgressRepo extends JpaRepository<SeasonProgress, Integer> {

   Optional<SeasonProgress> findByUsersShowProgressAndSeasonId(UsersShowProgress showProgress, int seasonId);

   @Query("SELECT sp FROM SeasonProgress sp " +
           "JOIN sp.usersShowProgress usp " +
           "WHERE usp.showId = :showId " +
           "AND usp.user = :user")
   List<SeasonProgress> findSeasonProgressForShowAndUser(Integer showId, User user);

}
