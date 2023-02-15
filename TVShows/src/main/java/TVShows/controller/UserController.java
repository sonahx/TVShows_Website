package TVShows.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import TVShows.domain.User;
import TVShows.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	UserController(UserService userService){
		this.userService = userService;
	}
	
	@GetMapping("/get/{id}")
	public String getUser(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "home";
	}
	
//	@GetMapping("/greeting")
//	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//		model.addAttribute("name", name);
//		return "greeting";
//	}

}
