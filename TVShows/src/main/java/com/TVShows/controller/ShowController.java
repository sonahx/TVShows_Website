package com.TVShows.controller;

import com.TVShows.domain.*;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.exceptions.ShowNotFoundException;
import com.TVShows.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final TVShowService showService;
    private final ShowCommentService commentService;
    private final UsersShowProgressService usersShowsService;
    private final SeasonProgressService seasonProgressService;
    private final MovieDbService movieDbService;
    private final SeasonService seasonService;
    private final StarRatingService starRatingService;

    @GetMapping("/{id}")
    public String singleShow(@PathVariable("id") Long id, Model model) {
        TVShow tvShow = showService.findShowById(id).orElse(null);
        User user = (User) model.getAttribute("authenticatedUser");

        if (tvShow != null) {
            model.addAttribute("show", tvShow);
            model.addAttribute("ShowComment", new ShowComment());
            model.addAttribute("Comments", commentService.findAllCommentsByShowId(tvShow.getId()));
            model.addAttribute("Seasons", seasonService.findAllSeasonsByTvShowId(tvShow.getId()));
            try {
                double voteAverage = Double.parseDouble(tvShow.getVoteAverage());
                StarRating starRating = starRatingService.calculateStarRating(voteAverage);
                model.addAttribute("starRating", starRating);
            } catch (NumberFormatException e) {
                model.addAttribute("starRating", new StarRating(0, 0, 10)); // Default to 0 stars
            }

            if (user != null) {
                Optional<UsersShowProgress> usersShowProgress = usersShowsService.createDefaultShowProgress(tvShow, user);
                model.addAttribute("usersShowProgress", usersShowProgress);
                seasonProgressService.createDefaultSeasonProgress(tvShow, user, usersShowProgress);
                List<SeasonProgress> seasonProgressList = seasonProgressService.findSeasonProgressForShowAndUser(tvShow, user);
                model.addAttribute("seasonProgressList", seasonProgressList);
            }
        }
        return "singleShow";
    }

    @PostMapping("/add")
    public String addShow(@RequestParam int showId, Model model) {
        try {
            TVShow show = movieDbService.createShowWithId(showId);
            model.addAttribute("show", show);
            return "redirect:/show/" + show.getId();
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("ShowComment") ShowComment text) {
        User user = (User) model.getAttribute("authenticatedUser");
        TVShow show = showService.findShowById(id).orElse(null);
        if (show != null && user != null && !text.getText().trim().isEmpty()) {

            ShowComment comment = new ShowComment();
            comment.setText(text.getText());
            comment.setAuthor(user);
            comment.setTvShow(show);
            comment.setDate(LocalDateTime.now());
            commentService.save(comment);
        }
        return "redirect:/show/" + id;
    }

    @PostMapping("/{showId}/comment/delete/{commentId}")
    public String removeComment(@PathVariable("showId") Long showId, @PathVariable("commentId") long commentId, Model model) {
        ShowComment comment = commentService.findShowCommentById(commentId);
        Optional<TVShow> show = showService.findShowById(showId);
        User user = (User) model.getAttribute("authenticatedUser");

        if (show.isPresent() && user != null && Objects.equals(user.getId(), comment.getAuthor().getId())) {
            commentService.delete(commentId);
            show.get().getComments().remove(comment);
            showService.updateShow(show.get());
            return "redirect:/show/" + showId;
        }
        return "error";
    }
}
