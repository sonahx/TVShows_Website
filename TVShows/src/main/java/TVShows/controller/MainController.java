package TVShows.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import TVShows.domain.TVShow;
import TVShows.domain.User;
import TVShows.service.TVShowService;
import TVShows.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	@SuppressWarnings("unused")
	private final TVShowService TVshowService;
	private final UserService userService;

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
	public String tvShowForm(Model model) {
		model.addAttribute("TVShow", new TVShow());
		return "tvshowform";
	}

	@GetMapping("/auth")
	public String auth(Model model) {
		model.addAttribute("user", new User());
		return "auth";
	}
	
	@GetMapping("/profile/{email}")
	public String profile(@PathVariable("email")String email, Model model) {
		User user = userService.getByEmail(email).orElseThrow(()-> 
		new UsernameNotFoundException("username not found"));
		model.addAttribute("user", user);
		return "profile";
	}
}
