package com.TVShows.service;

import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.repo.UsersShowProgressRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersShowProgressService {

    private final UsersShowProgressRepo repo;
    private final static Logger logger = LoggerFactory.getLogger(UsersShowProgressService.class);

    public void save(UsersShowProgress progress) {
        logger.info("User {} added to his list - {} - {}",
                progress.getUser().getName(),
                progress.getTvShow().getName(),
                progress.getStatus());
        repo.save(progress);
    }

    public List<UsersShowProgress> findAll() {
        return repo.findAll();
    }

    public List<UsersShowProgress> findByUser(User user) {
        return repo.findByUser(user);
    }

    public Optional<UsersShowProgress> findByShowAndUser(TVShow tvShow, User user) {
        return repo.findByTvShowAndUser(tvShow, user);
    }

    public void update(UsersShowProgress progress) {
        logger.info("User {} updated - {} - {} ",
                progress.getUser().getName(),
                progress.getTvShow().getName(),
                progress.getStatus());
        repo.save(progress);
    }

    public Optional<UsersShowProgress> findByTvShowAndUser(TVShow show, User user) {
        return repo.findByTvShowAndUser(show, user);
    }

    public void setPersonalScore(UsersShowProgress progress, int score) {
        logger.info("User {} setting personal score for {} - {}",
				progress.getUser().getName(),
				progress.getTvShow().getName(),
				score);
        progress.setPersonalScore(score);
    }

    public Optional<UsersShowProgress> createDefaultShowProgress(TVShow tvShow, User user){
        if (findByShowAndUser(tvShow, user).isEmpty()) {
            save(new UsersShowProgress(user, tvShow, ViewerStatus.DEFAULT));
        }
        return findByShowAndUser(tvShow, user);
    }
}
