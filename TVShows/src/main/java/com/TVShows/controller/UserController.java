package com.TVShows.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.TVShows.DTO.WatchingStatusRequest;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShows;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UserService;
import com.TVShows.service.UsersShowsService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TVShowService showService;
    private final UsersShowsService usersShowsService;

    @GetMapping("/get/{id}")
    public void getUserInfo(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);


    }

    @PostMapping("/addShow")
    @ResponseStatus(value = HttpStatus.OK)
    public void addShowToUser(@ModelAttribute WatchingStatusRequest request, Model model,
                              @ModelAttribute("authenticatedUser") User user) {
        Optional<TVShow> show = showService.findShowById(request.getShowId());
        UsersShows existingUsersShows = user.getShows().stream()
                .filter(s -> s.getTvShow().getId() == request.getShowId())
                .findFirst()
                .orElse(null);

        if (existingUsersShows != null) {
            // The show is already in the user's shows, so update the status
            existingUsersShows.setStatus(request.getStatus());
            usersShowsService.save(existingUsersShows);

        } else {
            // The show is not yet in the user's shows, so add a new UsersShows entity
            if (show.isPresent()) {
                UsersShows newUsersShows = new UsersShows(user, show.get(), request.getStatus());
                user.getShows().add(newUsersShows);
                usersShowsService.save(newUsersShows);
            }
        }

    }
}
