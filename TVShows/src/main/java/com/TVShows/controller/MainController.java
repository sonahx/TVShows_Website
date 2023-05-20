package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import com.TVShows.domain.NewsArticle;
import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.service.NewsArticleService;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UserService;
import com.TVShows.service.UsersShowProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final TVShowService TVshowService;
	private final UserService userService;
	private final NewsArticleService newsArticleService;

	@GetMapping("/")
	public String rootPage() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		Page<TVShow> page = TVshowService.findAllShowsWithPagination(0, 10);
		Page<NewsArticle> newsPage = newsArticleService.findAllShowsWithPagination(0, 5);

		model.addAttribute("page", page);
		model.addAttribute("newsPage", newsPage);
		model.addAttribute("NewsArticle", new NewsArticle());
		return "home";
	}

	@GetMapping("/shows")
	public String shows(Model model) {
		Page<TVShow> page = TVshowService.findAllShowsWithPagination(0, 10);
		model.addAttribute("page", page);
		return "redirect:/page?page=0&size=10";
	}

	@GetMapping("/page")
	public String switchPage(@RequestParam int page, @RequestParam int size, Model model) {
		Page<TVShow> pageAttr = TVshowService.findAllShowsWithPagination(page, size);
		model.addAttribute("page", pageAttr);
		return "shows";
	}

	@GetMapping("/tvshowform")
	public String tvShowForm(Model model) {
		model.addAttribute("TVShow", new TVShow());
		return "tvshowform";
	}

	@GetMapping("/auth")
	public String auth(Model model) {
		model.addAttribute("user", new User());
		return "auth";
	}
	
	@GetMapping("/profile")
	public String profile(@RequestParam("user") String name, Model model) {
		User profileUser = userService.findByUsername(name).orElseThrow(()->
		new UsernameNotFoundException("username not found"));
		model.addAttribute("user", profileUser);
		model.addAttribute("image", new ImageEncoder());
		return "profile";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/team")
	public String team() {
		return "team";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/policy")
	public String policy() {
		return "policy";
	}
}
