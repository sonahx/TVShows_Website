package com.TVShows.service;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.SeasonProgress;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.repo.SeasonProgressRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeasonProgressService {

    private final SeasonProgressRepo repo;
    private final SeasonService seasonService;

    public Optional<SeasonProgress> findByUsersShowProgressAndSeasonId(UsersShowProgress progress, int seasonId) {
        log.info("Looking for season progress by USP: {}, and season: {}", progress.getId(), seasonId);
        return repo.findByUsersShowProgressAndSeasonId(progress, seasonId);
    }

    public SeasonProgress save(SeasonProgress progress) {
        log.info("Saving new season progress");
        return repo.save(progress);
    }

    public void update(SeasonProgress progress) {
        log.info("Updating season progress: {}", progress.getId());
        repo.save(progress);
    }

    public List<SeasonProgress> findSeasonProgressForShowAndUser(int showId, User user) {
        log.info("Looking for season progress by show: {} and user: {}", showId, user.getName());
        return repo.findSeasonProgressForShowAndUser(showId, user);
    }

    public void createDefaultSeasonProgresses(int showId, User user, UsersShowProgress usersShowProgress) {

        if (findSeasonProgressForShowAndUser(showId, user).isEmpty() && usersShowProgress != null) {
            seasonService.findAllSeasonsByTvShowId(showId).forEach(season -> {
                SeasonProgress newProgress = new SeasonProgress(0, usersShowProgress, season.getId());
                newProgress.setMaxProgress(season.getEpisodes().size());
                save(newProgress);
                log.info("Creating default season progress maxProgress: {}, episode count: {}",
                        season.getEpisodes().size(), season.getEpisode_count());
            });
        }
    }

    public void setProgressToMaximum(WatchingStatusRequest request, UsersShowProgress progress) {
        if (request.getStatus().equals(ViewerStatus.COMPLETED)) {
            progress.getSeasonProgress().forEach(sp -> {
                sp.setProgress(sp.getMaxProgress());
            });
            log.info("Setting progress to maximum for progress: {}", progress.getId());
        }
    }
}
