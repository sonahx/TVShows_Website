package com.TVShows.service;

import com.TVShows.domain.Season;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeasonService {

    private final ShowService showService;
    private final ApiResponseSeasonService apiSeasonResponseService;

    public List<Season> findAllSeasonsByTvShowId(int showId) {
        log.info("Looking for all seasons by show id: {}", showId);
        return showService.findShowById(showId).orElse(null).getSeasons();
    }

    public Season findSeasonByNumber(int showId, int seasonNumber) {
        String url = "https://api.themoviedb.org/3/tv/" + showId + "/season/" + seasonNumber + "?language=en-US";
        return apiSeasonResponseService.sendRequest(url);
    }
}


