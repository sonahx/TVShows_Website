package com.TVShows.service;

import com.TVShows.domain.*;
import com.TVShows.repo.SeasonProgressRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonProgressService {

    private final SeasonProgressRepo repo;
    private final static Logger logger = LoggerFactory.getLogger(SeasonProgressService.class);

    public Optional<SeasonProgress> findByUsersShowProgressAndSeason(UsersShowProgress progress, Season season){
        return repo.findByUsersShowProgressAndSeason(progress, season);
    }

    public SeasonProgress save(SeasonProgress progress){
        return repo.save(progress);
    }

    public void update(SeasonProgress progress) {
        logger.info("User {}: updated {}, season {} to {}/{}",
                progress.getUsersShowProgress().getUser().getName(),
                progress.getUsersShowProgress().getTvShow().getName(),
                progress.getSeason().getSeason_number(),
                progress.getProgress(),
                progress.getSeason().getEpisode_count());
        repo.save(progress);
    }
    public List<SeasonProgress> findSeasonProgressForShowAndUser(TVShow tvShow, User user){
        return repo.findSeasonProgressForShowAndUser(tvShow, user);
    }
}
