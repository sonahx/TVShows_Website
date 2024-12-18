package com.TVShows.controller;

import com.TVShows.domain.ShowComment;
import com.TVShows.domain.StarRating;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;
    private final ShowCommentService commentService;
    private final SeasonService seasonService;
    private final UsersShowProgressService usersShowService;
    private final SeasonProgressService seasonProgressService;

    @GetMapping("/{id}")
    public String singleShow(@PathVariable("id") Integer showId, Model model) {
        TVShow tvShow = showService.findShowById(showId).orElse(null);
        User user = (User) model.getAttribute("authenticatedUser");

        if (tvShow != null) {
            model.addAttribute("show", tvShow);
            model.addAttribute("ShowComment", new ShowComment());
            model.addAttribute("Comments", commentService.findAllCommentsByShowId(showId));
            model.addAttribute("Seasons", seasonService.findAllSeasonsByTvShowId(showId));
            try {
                model.addAttribute("starRating", showService.parseStarRating(tvShow));
            } catch (NumberFormatException e) {
                model.addAttribute("starRating", new StarRating(0, 0, 10)); // Default to 0 stars
            }
            if (user != null) {
                showService.createDefaultProgresses(tvShow, user, model, usersShowService, seasonProgressService);
            }
        } else {
            return "error";
        }
        return "singleShow";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable("id") int id, Model model,
                             @ModelAttribute("ShowComment") ShowComment text) {
        User user = (User) model.getAttribute("authenticatedUser");
        TVShow show = showService.findShowById(id).orElse(null);
        if (show != null && user != null && !text.getText().trim().isEmpty()) {
            ShowComment comment = new ShowComment(text.getText(), user, id, LocalDateTime.now());
            commentService.save(comment);
        }
        return "redirect:/show/" + id;
    }

    @PostMapping("/{showId}/comment/delete/{commentId}")
    public String removeComment(@PathVariable("showId") int showId, @PathVariable("commentId") int commentId, Model model) {
        ShowComment comment = commentService.findShowCommentById(commentId);
        Optional<TVShow> show = showService.findShowById(showId);
        User user = (User) model.getAttribute("authenticatedUser");

        if (show.isPresent() && user != null && Objects.equals(user.getId(), comment.getAuthor().getId())) {
            commentService.delete(commentId);
            return "redirect:/show/" + showId;
        }
        return "error";
    }
}
