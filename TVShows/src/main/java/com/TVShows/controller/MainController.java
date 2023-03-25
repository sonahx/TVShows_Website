package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.service.TVShowService;
import com.TVShows.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final TVShowService TVshowService;
	private final UserService userService;

	@GetMapping("/")
	public String rootPage() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("shows", TVshowService.findAllShows());
		return "home";
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
		User user = userService.findByUsername(name).orElseThrow(()->
		new UsernameNotFoundException("username not found"));
		model.addAttribute("user", user);
		model.addAttribute("image", new ImageEncoder());
		return "profile";
	}
}
