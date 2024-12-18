package com.TVShows.service;

import com.TVShows.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShowService {
    private final ApiResponseShowService apiResponseService;
    private final ApiResponseShowListService apiResponseListService;
    private final StarRatingService starRatingService;

    public Optional<TVShow> findShowById(int id) {
        log.info("Looking for show by id '{}'", id);
        String url = "https://api.themoviedb.org/3/tv/" + id + "?language=en-US";
        return Optional.ofNullable(apiResponseService.sendRequest(url));
    }

    public List<TVShow> findShowsByKeyword(String keyword) {
        log.info("Looking for show by keyword '{}'", keyword);
        String url = "https://api.themoviedb.org/3/search/tv?query=" + keyword + "&include_adult=false&language=en-US&page=1";
        return apiResponseListService.sendRequest(url);
    }

    public List<TVShow> findPopularShows(int page) {
        log.info("Looking for popular shows : {}", page);
        String url = "https://api.themoviedb.org/3/tv/popular?language=en-US&page=" + page;
        return apiResponseListService.sendRequest(url);
    }

    public List<TVShow> findDailyTrendingShows() {
        log.info("Looking for daily trending shows");
        String url = "https://api.themoviedb.org/3/trending/tv/day?language=en-US";
        return apiResponseListService.sendRequest(url);
    }

    public List<TVShow> findWeeklyTrendingShows() {
        log.info("Looking for weekly popular shows");
        String url = "https://api.themoviedb.org/3/trending/tv/week?language=en-US";
        return apiResponseListService.sendRequest(url);
    }

    public List<TVShow> findTopRatedShows(int page) {
        log.info("Looking for top rated shows : {}", page);
        String url = "https://api.themoviedb.org/3/discover/tv?include_adult=false&language=en-US&page=" + page + "&sort_by=vote_average.desc&vote_count.gte=200";
        return apiResponseListService.sendRequest(url);
    }

    public List<TVShow> findAiringTodayShows(int page) {
        log.info("Looking for airing today shows : {}", page);
        String url = "https://api.themoviedb.org/3/tv/airing_today?language=en-US&page=" + page;
        return apiResponseListService.sendRequest(url);
    }

    public List<TVShow> findOnTheAirShows(int page) {
        log.info("Looking for on the air today shows : {}", page);
        String url = "https://api.themoviedb.org/3/tv/on_the_air?language=en-US&page=" + page;
        return apiResponseListService.sendRequest(url);
    }

    public void createDefaultProgresses(TVShow tvShow, User user, Model model,
                                        UsersShowProgressService usersShowsService, SeasonProgressService seasonProgressService) {
        Optional<UsersShowProgress> usersShowProgress =
                usersShowsService.createDefaultShowProgress(tvShow.getId(), tvShow.getName(), tvShow.getNumber_of_episodes(), user);
        seasonProgressService.createDefaultSeasonProgresses(tvShow.getId(), user, usersShowProgress.orElse(null));
        List<SeasonProgress> seasonProgressList = seasonProgressService.findSeasonProgressForShowAndUser(tvShow.getId(), user);
        model.addAttribute("usersShowProgress", usersShowProgress);
        model.addAttribute("seasonProgressList", seasonProgressList);
    }

    public StarRating parseStarRating(TVShow tvShow) {
        double voteAverage = Double.parseDouble(tvShow.getVoteAverage());
        return starRatingService.calculateStarRating(voteAverage);
    }

    public List<TVShow> findShowsByCategory(String category, int page) {
        switch (category.trim().toLowerCase()) {
            case "top-rated" -> {
                return findTopRatedShows(page);
            }
            case "popular" -> {
                return findPopularShows(page);
            }
            case "on-the-air" -> {
                return findOnTheAirShows(page);
            }
            case "airing-today" -> {
                return findAiringTodayShows(page);
            }
            default -> {
                return findTopRatedShows(page);
            }
        }
    }
}
