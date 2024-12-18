package com.TVShows.service;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.Season;
import com.TVShows.domain.SeasonProgress;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.repo.UsersShowProgressRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersShowProgressService {

    private final UsersShowProgressRepo repo;
    private final SeasonService seasonService;
    private final SeasonProgressService seasonProgressService;

    public void save(UsersShowProgress progress) {
        log.info("Saving new USP for show: {}", progress.getShowId());
        repo.save(progress);
    }

    public List<UsersShowProgress> findAll() {
        log.info("Looking for all USP");
        return repo.findAll();
    }

    public List<UsersShowProgress> findByUser(User user) {
        log.info("Looking for all USP by user: {}", user.getName());
        return repo.findByUser(user);
    }

    public Optional<UsersShowProgress> findByShowAndUser(int showId, User user) {
        log.info("Looking for USP");
        return repo.findByShowIdAndUser(showId, user);
    }

    public void update(UsersShowProgress progress) {
        log.info("User {} updated USP for - {} - {}",
                progress.getUser().getName(),
                progress.getShowId(),
                progress.getStatus());
        repo.save(progress);
    }

    public Optional<UsersShowProgress> findByTvShowAndUser(int showId, User user) {
        log.info("Looking for USP by show id: {} and user: {}", showId, user.getName());
        return repo.findByShowIdAndUser(showId, user);
    }

    public void setPersonalScore(UsersShowProgress progress, int score) {
        log.info("User {} set personal score for {} - {}",
                progress.getUser().getName(),
                progress.getShowId(),
                score);
        progress.setPersonalScore(score);
    }

    public Optional<UsersShowProgress> createDefaultShowProgress(int showId, String showName,
                                                                 int totalProgress, User user) {
        if (findByShowAndUser(showId, user).isEmpty()) {
            save(new UsersShowProgress(user, showId, showName, totalProgress, ViewerStatus.DEFAULT));
            log.info("Creating new default USP for show: {} user: {}", showId, user.getName());
        }
        return findByShowAndUser(showId, user);
    }

    public void createUsersShowProgress(User user, Integer showId, String showName, int totalProgress, WatchingStatusRequest request) {
        UsersShowProgress newUsersShows = new UsersShowProgress(user, showId, showName, totalProgress, request.getStatus());
        user.getShowProgresses().add(newUsersShows);
        save(newUsersShows);
    }

    public void increment(UsersShowProgress showProgress, SeasonProgress seasonProgress,
                          User user, int showId, int seasonId, int seasonNumber) {
        Season season = seasonService.findSeasonByNumber(showId, seasonNumber);

        if (showProgress == null && user != null) {
            UsersShowProgress newUsersShowProgress = new UsersShowProgress(user, showId, ViewerStatus.WATCHING);
            user.getShowProgresses().add(newUsersShowProgress);
            showProgress = newUsersShowProgress;
            save(newUsersShowProgress);
        }
        if (seasonProgress == null & user != null) { //create season progress if its null
            SeasonProgress newSeasonProgress = new SeasonProgress(showProgress, seasonId);
            seasonProgress = newSeasonProgress;
            seasonProgressService.save(newSeasonProgress);
        }
        if (showProgress != null && seasonProgress != null //increment
                && seasonProgress.getProgress() < season.getEpisodes().size()) {
            showProgress.setCurrentProgress(seasonProgress.getProgress() + 1);
            seasonProgress.setProgress(seasonProgress.getProgress() + 1);
            seasonProgressService.update(seasonProgress);

            showProgress.setStatus(ViewerStatus.WATCHING); //set status to WATCHING
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
