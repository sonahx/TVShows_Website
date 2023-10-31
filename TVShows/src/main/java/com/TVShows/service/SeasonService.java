package com.TVShows.service;

import com.TVShows.domain.Season;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.repo.SeasonRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepo repo;

    public List<Season> findAllSeasonsByTvShowId(Long id){
        return repo.findAllSeasonsByShowId(id);
    }

    public void save(Season season){
        repo.save(season);
    }

    public Optional<Season> findSeasonById(Long id){
        return repo.findSeasonById(id);
    };
    }


