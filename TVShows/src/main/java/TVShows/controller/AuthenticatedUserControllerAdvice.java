package TVShows.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import TVShows.domain.User;
import TVShows.service.UserService;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class AuthenticatedUserControllerAdvice {

	private final UserService userService;

	@ModelAttribute("authenticatedUser")
	public User authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof User) {
				return (User) principal;
			} else if (principal instanceof String email) {
				Optional<User> optionalUser = userService.findByEmail(email);
				return optionalUser.orElse(null);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}