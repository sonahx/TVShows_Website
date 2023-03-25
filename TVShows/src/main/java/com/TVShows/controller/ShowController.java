package com.TVShows.controller;

import com.TVShows.domain.ShowComment;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.service.ShowCommentService;
import com.TVShows.service.TVShowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final TVShowService showService;
    private final ShowCommentService commentService;


    @PostMapping("/add")
    public String addShow(@ModelAttribute TVShow tvShow, Model model) {
        showService.createShow(tvShow);
        model.addAttribute("show", tvShow);
        return "tvshowform";
    }

    @GetMapping("/{id}")
    public String singleShow(@PathVariable("id") Long id, Model model) {
        Optional<TVShow> tvShow = showService.findShowById(id);
        if (tvShow.isPresent()) {
            model.addAttribute("show", tvShow.get());
            model.addAttribute("ShowComment", new ShowComment());
        }
        return "singleShow";
    }

    @PostMapping("/{id}/comment")
    public void addComment(@PathVariable("id") Long id, Model model,
                           @ModelAttribute("ShowComment") ShowComment text){
        User user = (User) model.getAttribute("authenticatedUser");
        Optional<TVShow> show = showService.findShowById(id);
        if (show.isPresent()) {
            //create and save comment
            ShowComment comment = new ShowComment();
            comment.setText(text.getText());
            comment.setAuthor(user);
            comment.setTvShow(show.get());
            comment.setDate(LocalDateTime.now());
            commentService.save(comment);

            //update TVShow
            List<ShowComment> currentComments = show.get().getComments();
            currentComments.add(comment);
            show.get().setComments(currentComments);
            showService.updateShow(show.get());
        }
    }
}
