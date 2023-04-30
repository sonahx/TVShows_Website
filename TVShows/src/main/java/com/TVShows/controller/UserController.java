package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.WrongOperationException;
import com.TVShows.repo.UsersShowProgressRepo;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UserService;
import com.TVShows.service.UsersShowProgressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TVShowService showService;
    private final UsersShowProgressService usersShowProgressService;
    private final UsersShowProgressRepo usersShowProgressRepo;

    @GetMapping("/user/get/{id}")
    public void getUserInfo(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
    }

    @PostMapping("/{id}/image/upload")
    public void uploadImage(@ModelAttribute("image") ImageEncoder image, @PathVariable Long id,
                            Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        User authenticatedUser = (User) model.getAttribute("authenticatedUser");

        if (authenticatedUser != null && authenticatedUser.getId().equals(id)) {
            User toUpdate = image.encodeImage(authenticatedUser);
            userService.updateUser(toUpdate);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @PostMapping("/user/addShow")
    public String addShowToUser(@ModelAttribute WatchingStatusRequest request, Model model,
                                @ModelAttribute("authenticatedUser") User user) {
        Optional<TVShow> show = showService.findShowById(request.getShowId());
        Optional<UsersShowProgress> existingProgress = usersShowProgressRepo.findByTvShowAndUser(show.orElse(null), user);

        // The show is already in the user's shows, so update the status
        if (existingProgress.isPresent()) {
            existingProgress.get().setStatus(request.getStatus());
            usersShowProgressService.update(existingProgress.get());
        }
        // The show is not yet in the user's shows, so add a new UsersShows entity
        else {
            UsersShowProgress newUsersShows = new UsersShowProgress(user, show.orElse(null), 0, request.getStatus());
            user.getShows().add(newUsersShows);
            usersShowProgressService.save(newUsersShows);
        }
        return "home";
    }

    @PostMapping("/user/{show}/{operation}")
    public String changeEpisodeProgress(@PathVariable TVShow show, @PathVariable String operation, Model model) {
        User user = (User) model.getAttribute("authenticatedUser");
        Optional<UsersShowProgress> progress = usersShowProgressService.findByShowAndUser(show, user);

        // increment operation if status was set before
       if (progress.isPresent() && operation.equals("increment") &&
                show.getEpisodesNumber() > progress.get().getEpisodeProgress()) {
            progress.get().setEpisodeProgress(progress.get().getEpisodeProgress() + 1);

            // if progress reaches max value, set status to COMPLETED
            if(progress.get().getEpisodeProgress().equals(show.getEpisodesNumber())){
                progress.get().setStatus(ViewerStatus.COMPLETED);
            }
            usersShowProgressService.update(progress.get());
        }
        // decrement operation if status was set before
        else if (progress.isPresent() && operation.equals("decrement") &&
                progress.get().getEpisodeProgress() > 0) {
            progress.get().setEpisodeProgress(progress.get().getEpisodeProgress() - 1);
            usersShowProgressService.update(progress.get());
        }
        // if episode progress incremented without changing a status, set status to WATCHING
        else if (progress.isEmpty() && user != null && operation.equals("increment")) {
            UsersShowProgress newUsersShowProgress = new UsersShowProgress(user, show,1, ViewerStatus.WATCHING);
            user.getShows().add(newUsersShowProgress);
            usersShowProgressService.save(newUsersShowProgress);
        }
        else {
            throw new WrongOperationException("Wrong operation credentials");
        }
        return "home";
    }
}
