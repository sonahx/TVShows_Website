package com.TVShows.controller;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.*;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.repo.SeasonProgressRepo;
import com.TVShows.service.SeasonProgressService;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UsersShowProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ShowProgressController {

    private final TVShowService showService;
    private final UsersShowProgressService usersShowProgressService;
    private final SeasonProgressService seasonProgressService;
    private final SeasonProgressRepo seasonProgressRepo;

    @PostMapping("/addShow")
    public String addShowToUser(@ModelAttribute WatchingStatusRequest request,
                                @ModelAttribute("authenticatedUser") User user) {
        TVShow show = showService.findShowById(request.getShowId()).orElse(null);
        UsersShowProgress existingProgress = usersShowProgressService.findByTvShowAndUser(show, user).orElse(null);

        // The show is already in the user's shows, so update the status
        if (existingProgress != null) {
            existingProgress.setStatus(request.getStatus());
            usersShowProgressService.update(existingProgress);
        }
        // The show is not yet in the user's shows, so make a new one
        else {
            UsersShowProgress newUsersShows = new UsersShowProgress(user, show, request.getStatus());
            user.getShowProgresses().add(newUsersShows);
            usersShowProgressService.save(newUsersShows);
        }
        return "home";
    }

    @PostMapping("/{show}/{season}/{operation}")
    public String changeEpisodeProgress(@PathVariable TVShow show,
                                        @PathVariable Season season,
                                        @PathVariable String operation,
                                        Model model) {
        User user = (User) model.getAttribute("authenticatedUser");
        UsersShowProgress showProgress = usersShowProgressService.findByShowAndUser(show, user).orElse(null);
        SeasonProgress seasonProgress = seasonProgressService.findByUsersShowProgressAndSeason(showProgress, season).orElse(null);

        switch (operation) {
            case "increment" -> {
                //create show progress if its null
                if (showProgress == null && user != null) {
                    UsersShowProgress newUsersShowProgress = new UsersShowProgress(user, show, ViewerStatus.WATCHING);
                    user.getShowProgresses().add(newUsersShowProgress);
                    showProgress = newUsersShowProgress;
                    usersShowProgressService.save(newUsersShowProgress);
                }
                //create season progress if its null
                if (seasonProgress == null & user != null) {
                    SeasonProgress newSeasonProgress = new SeasonProgress(showProgress, season);
                    seasonProgress = newSeasonProgress;
                    seasonProgressRepo.save(newSeasonProgress);
                }
                //increment
                if (showProgress != null && seasonProgress != null && seasonProgress.getProgress() < season.getEpisode_count()) {
                    seasonProgress.setProgress(seasonProgress.getProgress() + 1);
                    seasonProgressService.update(seasonProgress);

                    //set status to WATCHING
                    showProgress.setStatus(ViewerStatus.WATCHING);
                    usersShowProgressService.update(showProgress);
                } else {
                    throw new WrongOperationException("Wrong operation credentials for increment");
                }
            }
            case "decrement" -> {
                if (showProgress != null && seasonProgress != null && seasonProgress.getProgress() > 0) {
                    seasonProgress.setProgress(seasonProgress.getProgress() - 1);
                    seasonProgressService.update(seasonProgress);
                } else {
                    throw new WrongOperationException("Wrong operation credentials for decrement");
                }
            }
            default -> throw new WrongOperationException("Wrong operation credentials");
        }
        return "home";
    }
}
