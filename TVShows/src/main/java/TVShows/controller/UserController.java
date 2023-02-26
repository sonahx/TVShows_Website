package TVShows.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TVShows.DTO.WatchingStatusRequest;
import TVShows.domain.TVShow;
import TVShows.domain.User;
import TVShows.domain.UsersShows;
import TVShows.service.TVShowService;
import TVShows.service.UserService;
import TVShows.service.UsersShowsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final TVShowService showService;
	private final UsersShowsService usersShowsService;
//	private final AuthenticationManager authenticationManager;
	
	
	@GetMapping("/get/{id}")
	public String getUser(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "home";
	}

	@PostMapping("/addShow")
	public String addShowToUser(@ModelAttribute WatchingStatusRequest request, Model model, 
			Authentication authentication) {
	    User user = (User) authentication.getPrincipal();
	    TVShow show = showService.findShowById(request.getShowId());
	    UsersShows existingUsersShows = user.getShows().stream()
	        .filter(s -> s.getTvShow().getId() == request.getShowId())
	        .findFirst()
	        .orElse(null);

	    if (existingUsersShows != null) {
	        // The show is already in the user's shows, so update the status
	        existingUsersShows.setStatus(request.getStatus());
	        usersShowsService.save(existingUsersShows);

	    } else {
	        // The show is not yet in the user's shows, so add a new UsersShows entity
	        UsersShows newUsersShows = new UsersShows(user, show, request.getStatus());
	        user.getShows().add(newUsersShows);
	        usersShowsService.save(newUsersShows);
	    }

	    return "home";
	}
	
	
//	private User getUserPrincipal(){
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		 Authentication auth = securityContext.getAuthentication();
//		User user = (User) auth.getPrincipal();
//		return user;
//	}
}
