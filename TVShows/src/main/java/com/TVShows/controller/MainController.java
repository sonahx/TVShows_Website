package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import com.TVShows.DTO.PageDTO;
import com.TVShows.domain.NewsArticle;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.service.NewsArticleService;
import com.TVShows.service.ShowService;
import com.TVShows.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ShowService showService;
    private final UserService userService;
    private final NewsArticleService newsArticleService;

    @GetMapping("/")
    public String rootPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<TVShow> page = showService.findDailyTrendingShows();
        Page<NewsArticle> newsPage = newsArticleService.findAllArticlesWithPagination(0, 5);

        model.addAttribute("page", page);
        model.addAttribute("newsPage", newsPage);
        model.addAttribute("NewsArticle", new NewsArticle());
        return "home";
    }

    @GetMapping("/shows")
    public String shows(@RequestParam(defaultValue = "top-rated") String category, @RequestParam(defaultValue = "1") int page, Model model) {
        List<TVShow> shows = showService.findShowsByCategory(category, page);
        int totalPages = 30;
		model.addAttribute("category", category);
        model.addAttribute("page", new PageDTO(page, totalPages, shows));
        return "shows";
    }

    @GetMapping("/tvshowform")
    public String tvShowForm(Model model) {
        model.addAttribute("TVShow", new TVShow());
        return "tvshowform";
    }

    @GetMapping("/auth")
    public String auth(@RequestParam(name = "error", required = false) String error, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("param", new HashMap<String, Object>() {{
            put("error", error);
        }});
        return "auth";
    }

    @GetMapping("/profile")
    public String profile(@RequestParam("user") String name, Model model) {
        User user = userService.findByUsername(name).orElse(null);

        if (user != null) {
            long nonDefaultProgressCount = user.getShowProgresses().stream()
                    .filter(progress -> progress.getStatus() != ViewerStatus.DEFAULT)
                    .count();
            model.addAttribute("nonDefaultProgressCount", nonDefaultProgressCount);
            model.addAttribute("user", user);
            model.addAttribute("image", new ImageEncoder());
            return "profile";
        }
        return "error";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/policy")
    public String policy() {
        return "policy";
    }
}
