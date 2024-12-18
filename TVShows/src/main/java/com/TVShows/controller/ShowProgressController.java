package com.TVShows.controller;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.SeasonProgress;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.service.SeasonProgressService;
import com.TVShows.service.UsersShowProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ShowProgressController {

    private final UsersShowProgressService usersShowProgressService;
    private final SeasonProgressService seasonProgressService;

    @PostMapping("/addShow")
    public String addShowToUser(@ModelAttribute WatchingStatusRequest request,
                                @ModelAttribute("authenticatedUser") User user, Model model) {
        UsersShowProgress existingProgress = usersShowProgressService.findByTvShowAndUser(request.getShowId(), user).orElse(null);
        TVShow tvShow = (TVShow) model.getAttribute("show");
        // The show is already in the user's shows, so update the status
        if (existingProgress != null) {
            // complete progress if request status = COMPLETED
            seasonProgressService.setProgressToMaximum(request, existingProgress);
            existingProgress.setStatus(request.getStatus());
            usersShowProgressService.update(existingProgress);
        }
        // The show is not yet in the user's shows, so make a new one
        else {
            usersShowProgressService.createUsersShowProgress(user, tvShow.getId(), tvShow.getName(), tvShow.getNumber_of_episodes(), request);
        }
        return "home";
    }

    @PostMapping("/{showId}/score/{score}")
    public String changePersonalScore(@PathVariable int showId, Model model,
                                      @RequestParam(name = "personalScore", defaultValue = "0") Integer personalScore) {
        User user = (User) model.getAttribute("authenticatedUser");
        UsersShowProgress showProgress = usersShowProgressService.findByShowAndUser(showId, user).orElse(null);

        if (user != null & showProgress != null) {
            usersShowProgressService.setPersonalScore(showProgress, personalScore);
            usersShowProgressService.update(showProgress);
            return "redirect:/show/" + showId;
        }
        return "error";
    }

    @PostMapping("/{showId}/{seasonId}/{seasonNumber}/{operation}")
    public String changeEpisodeProgress(@PathVariable int showId, @PathVariable int seasonId,
                                        @PathVariable int seasonNumber, @PathVariable String operation, Model model) {
        User user = (User) model.getAttribute("authenticatedUser");
        UsersShowProgress showProgress = usersShowProgressService.findByShowAndUser(showId, user).orElse(null);
        SeasonProgress seasonProgress = seasonProgressService.findByUsersShowProgressAndSeasonId(showProgress, seasonId).orElse(null);

        switch (operation) {
            case "increment" -> {
                usersShowProgressService.increment(showProgress, seasonProgress, user, showId, seasonId, seasonNumber);
            }
            case "decrement" -> {
                usersShowProgressService.decrement(showProgress, seasonProgress);
            }
            default -> throw new WrongOperationException("Wrong operation credentials");
        }
        return "home";
    }
}
