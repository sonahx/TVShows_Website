package com.TVShows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TVShows.domain.TVShow;
import com.TVShows.service.TVShowService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final TVShowService showService;


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
        }
        return "singleShow";
    }
}
