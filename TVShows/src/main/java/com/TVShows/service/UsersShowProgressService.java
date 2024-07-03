package com.TVShows.service;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.*;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.WrongOperationException;
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
    private final SeasonProgressService seasonProgressService;
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

    public Optional<UsersShowProgress> createDefaultShowProgress(TVShow tvShow, User user) {
        if (findByShowAndUser(tvShow, user).isEmpty()) {
            save(new UsersShowProgress(user, tvShow, ViewerStatus.DEFAULT));
        }
        return findByShowAndUser(tvShow, user);
    }

    public void createUsersShowProgress(User user, TVShow show, WatchingStatusRequest request) {
        UsersShowProgress newUsersShows = new UsersShowProgress(user, show, request.getStatus());
        user.getShowProgresses().add(newUsersShows);
        save(newUsersShows);
    }

    public void increment(UsersShowProgress showProgress, SeasonProgress seasonProgress,
                          User user, TVShow show, Season season) {
        if (showProgress == null && user != null) {
            UsersShowProgress newUsersShowProgress = new UsersShowProgress(user, show, ViewerStatus.WATCHING);
            user.getShowProgresses().add(newUsersShowProgress);
            showProgress = newUsersShowProgress;
            save(newUsersShowProgress);
        }
        //create season progress if its null
        if (seasonProgress == null & user != null) {
            SeasonProgress newSeasonProgress = new SeasonProgress(showProgress, season);
            seasonProgress = newSeasonProgress;
            seasonProgressService.save(newSeasonProgress);
        }
        //increment
        if (showProgress != null && seasonProgress != null && seasonProgress.getProgress() < season.getEpisode_count()) {
            seasonProgress.setProgress(seasonProgress.getProgress() + 1);
            seasonProgressService.update(seasonProgress);

            //set status to WATCHING
            showProgress.setStatus(ViewerStatus.WATCHING);
            update(showProgress);
        } else {
            throw new WrongOperationException("Wrong operation credentials for increment");
        }
    }

    public void decrement(UsersShowProgress showProgress, SeasonProgress seasonProgress) {
        if (showProgress != null && seasonProgress != null && seasonProgress.getProgress() > 0) {
            seasonProgress.setProgress(seasonProgress.getProgress() - 1);
            seasonProgressService.update(seasonProgress);
        } else {
            throw new WrongOperationException("Wrong operation credentials for decrement");
        }
    }
}
