package TVShows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import TVShows.DTO.AuthRequest;
import TVShows.DTO.RegisterRequest;
import TVShows.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {	
	
	private final AuthenticationService service;
	
//	@GetMapping("/login")
//	public String login(Model model, String error, String logout) {
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
//		if (logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
//		return "auth";
//	}
	
	
//	  @PostMapping("/authenticate")
	  @RequestMapping(value = "/authenticate", method = { RequestMethod.GET, RequestMethod.POST })
	  public String authenticate(@ModelAttribute AuthRequest request) {
		  service.authenticate(request);
	    return "auth";
	  }

	@PostMapping("/register")
	public String register(@ModelAttribute RegisterRequest request, Model model) {
		service.register(request);
		model.addAttribute("user", request);
		return "auth";
	}
}
