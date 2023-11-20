package com.TVShows.controller;

import com.TVShows.domain.TVShow;
import com.TVShows.service.TVShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final TVShowService showService;

    @GetMapping("/search/{keyword}")
    public String search(@RequestParam String keyword, Model model) {
        List<TVShow> shows = showService.findShowsByKeyword(keyword);

        if (!shows.isEmpty() && keyword.length() >= 3) {
            model.addAttribute("Keyword", keyword);
            model.addAttribute("Shows", shows);
        }
        return "searchShows";
    }
}
