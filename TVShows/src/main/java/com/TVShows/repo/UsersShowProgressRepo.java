package com.TVShows.repo;

import com.TVShows.domain.TvShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersShowProgressRepo extends JpaRepository<UsersShowProgress, Long>{

    List<UsersShowProgress> findByUser(User user);

    Optional<UsersShowProgress> findByTvShowAndUser(TvShow tvShow, User user);
}
