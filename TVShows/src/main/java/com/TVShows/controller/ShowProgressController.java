package com.TVShows.controller;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.*;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.repo.SeasonProgressRepo;
import com.TVShows.service.SeasonProgressService;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UsersShowProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            // complete progress if request status = COMPLETED
            seasonProgressService.setProgressToMaximum(request, existingProgress);

            existingProgress.setStatus(request.getStatus());
            usersShowProgressService.update(existingProgress);
        }
        // The show is not yet in the user's shows, so make a new one
        else {
            usersShowProgressService.createUsersShowProgress(user, show, request);
        }
        return "home";
    }

    @PostMapping("/{show}/score/{score}")
    public String changePersonalScore(@PathVariable TVShow show,
                                      @RequestParam(name = "personalScore", defaultValue = "0") Integer personalScore,
                                      Model model) {
        User user = (User) model.getAttribute("authenticatedUser");
        UsersShowProgress showProgress = usersShowProgressService.findByShowAndUser(show, user).orElse(null);

        if (user != null && show != null & showProgress != null) {
            usersShowProgressService.setPersonalScore(showProgress, personalScore);
            usersShowProgressService.update(showProgress);
            return "redirect:/show/" + show.getId();
        }
        return "error";
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
                usersShowProgressService.increment(showProgress, seasonProgress, user, show, season);
            }
            case "decrement" -> {
                usersShowProgressService.decrement(showProgress, seasonProgress);
            }
            default -> throw new WrongOperationException("Wrong operation credentials");
        }
        return "home";
    }
}
