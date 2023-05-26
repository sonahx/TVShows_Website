package com.TVShows.controller;

import com.TVShows.domain.ShowComment;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.service.ShowCommentService;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UsersShowProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final TVShowService showService;
    private final ShowCommentService commentService;
    private final UsersShowProgressService usersShowsService;

    @PostMapping("/add")
    public String addShow(@ModelAttribute TVShow tvShow, Model model) {
        showService.createShow(tvShow);
        model.addAttribute("show", tvShow);
        return "redirect:/show/" + tvShow.getId();
    }

    @GetMapping("/{id}")
    public String singleShow(@PathVariable("id") Long id, Model model) {
        TVShow tvShow = showService.findShowById(id).orElse(null);
        User user = (User) model.getAttribute("authenticatedUser");
        if (tvShow != null) {
            model.addAttribute("show", tvShow);
            model.addAttribute("ShowComment", new ShowComment());
            if (user != null) {
                Optional<UsersShowProgress> usersShowProgress = usersShowsService.findByShowAndUser(tvShow, user);
                model.addAttribute("usersShowProgress", usersShowProgress);
            }
        }
        return "singleShow";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("ShowComment") ShowComment text) {
        User user = (User) model.getAttribute("authenticatedUser");
        TVShow show = showService.findShowById(id).orElse(null);
        if (show != null && user != null && text.getText().trim().length() >= 1) {
            //create and save comment
            ShowComment comment = new ShowComment();
            comment.setText(text.getText());
            comment.setAuthor(user);
            comment.setTvShow(show);
            comment.setDate(LocalDateTime.now());
            commentService.save(comment);

            //update TVShow
            List<ShowComment> currentComments = show.getComments();
            currentComments.add(comment);
            show.setComments(currentComments);
            showService.updateShow(show);
        }
        return "redirect:/show/" + id;
    }
}
