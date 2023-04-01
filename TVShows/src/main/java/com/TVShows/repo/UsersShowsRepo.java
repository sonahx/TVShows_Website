package com.TVShows.repo;

import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersShowsRepo extends JpaRepository<UsersShows, Long>{

    Optional<UsersShows> findByTvShowAndUser(TVShow tvShow, User user);
}
