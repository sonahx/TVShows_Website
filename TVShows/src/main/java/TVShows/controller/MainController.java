package TVShows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import TVShows.domain.TVShow;
import TVShows.domain.User;
import TVShows.service.TVShowService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	@SuppressWarnings("unused")
	private final TVShowService TVshowService;

	@GetMapping("/")
	public String rootPage() {
		return "redirect:/home";
	}

//	@GetMapping("/home")
//	public String home(Model model) {
//		model.addAttribute("shows", TVshowService.findAllShows());
//		return "home";
//	}

	@GetMapping("/tvshowform")
	public String greetingForm(Model model) {
		model.addAttribute("TVShow", new TVShow());
		return "tvshowform";
	}

	@GetMapping("/auth")
	public String auth(Model model) {
		model.addAttribute("user", new User());
		return "auth";
	}

}
