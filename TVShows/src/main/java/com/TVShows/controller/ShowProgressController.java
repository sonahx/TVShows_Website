package com.TVShows.controller;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.repo.UsersShowProgressRepo;
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
    private final UsersShowProgressRepo usersShowProgressRepo;

    @PostMapping("/addShow")
    public String addShowToUser(@ModelAttribute WatchingStatusRequest request,
                                @ModelAttribute("authenticatedUser") User user) {
        TVShow show = showService.findShowById(request.getShowId()).orElse(null);
        UsersShowProgress existingProgress = usersShowProgressRepo.findByTvShowAndUser(show, user).orElse(null);

        // The show is already in the user's shows, so update the status
        if (existingProgress != null) {
            if(request.getStatus().equals(ViewerStatus.COMPLETED)){
                existingProgress.setEpisodeProgress(existingProgress.getTvShow().getEpisodesNumber());
            }
            existingProgress.setStatus(request.getStatus());
            usersShowProgressService.update(existingProgress);
        }
        // The show is not yet in the user's shows, so make a new one
        else {
            UsersShowProgress newUsersShows = new UsersShowProgress(user, show, 0, request.getStatus());
            if(request.getStatus().equals(ViewerStatus.COMPLETED)){
                newUsersShows.setEpisodeProgress(newUsersShows.getTvShow().getEpisodesNumber());
            }
            user.getShows().add(newUsersShows);
            usersShowProgressService.save(newUsersShows);
        }
        return "home";
    }

    @PostMapping("/{show}/{operation}")
    public String changeEpisodeProgress(@PathVariable TVShow show, @PathVariable String operation, Model model) {
        User user = (User) model.getAttribute("authenticatedUser");
        UsersShowProgress progress = usersShowProgressService.findByShowAndUser(show, user)
                .orElse(null);

        switch (operation) {
            case "increment" -> {
                if (progress != null && show.getEpisodesNumber() > progress.getEpisodeProgress()) {
                    progress.setEpisodeProgress(progress.getEpisodeProgress() + 1);

                    // Set status COMPLETED if reached max value
                    if (progress.getEpisodeProgress().equals(show.getEpisodesNumber())) {
                        progress.setStatus(ViewerStatus.COMPLETED);
                    }
                    usersShowProgressService.update(progress);
                }
                // Create progress if it's null
                else if (progress == null && user != null) {
                    UsersShowProgress newUsersShowProgress = new UsersShowProgress(user, show, 1, ViewerStatus.WATCHING);
                    user.getShows().add(newUsersShowProgress);
                    usersShowProgressService.save(newUsersShowProgress);
                } else {
                    throw new WrongOperationException("Wrong operation credentials for increment");
                }
            }
            case "decrement" -> {
                if (progress != null && progress.getEpisodeProgress() > 0) {
                    progress.setEpisodeProgress(progress.getEpisodeProgress() - 1);
                    usersShowProgressService.update(progress);
                } else {
                    throw new WrongOperationException("Wrong operation credentials for decrement");
                }
            }
            default -> throw new WrongOperationException("Wrong operation credentials");
        }
        return "home";
    }
}
