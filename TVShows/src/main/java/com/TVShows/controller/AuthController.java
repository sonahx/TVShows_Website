package com.TVShows.controller;

import com.TVShows.DTO.RegisterRequest;
import com.TVShows.domain.User;
import com.TVShows.exceptions.EmailAlreadyTakenException;
import com.TVShows.exceptions.UsernameAlreadyTakenException;
import com.TVShows.exceptions.WrongCredentialsException;
import com.TVShows.service.AuthService;
import com.TVShows.service.ConfirmationTokenService;
import com.TVShows.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
@SessionAttributes({"user", "email"})
public class AuthController {

    private final AuthService authService;
    private final ConfirmationTokenService tokenService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, Model model) {
        model.addAttribute("user", request);
        try {
            authService.register(request);
        } catch (UsernameAlreadyTakenException e) {
            return "redirect:/auth?error=username";
        } catch (EmailAlreadyTakenException e) {
            return "redirect:/auth?error=email";
        } catch (WrongCredentialsException e) {
            return "redirect:/auth?error=wrong";
        }
        return "redirect:/auth?success=register";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        if (token != null) {
            tokenService.confirmRegisterToken(token);
            return "redirect:/auth?success=confirmRegister";
        }
        return "error";
    }

	// sends email with a link to a password reset form
	@PostMapping("forgotPassword")
    public String forgotPassword(@RequestParam String email, Model model) {
		if(email != null) {
			authService.forgotPassword(email);
            model.addAttribute("email", email);
			return "redirect:/auth?success=forgotPassword";
		}
		return "error";
    }

	// link from email
    @GetMapping("/passwordReset")
    public String passwordResetForm(@RequestParam("token") String token, Model model) {
        User user = tokenService.confirmResetToken(token);
        model.addAttribute("user", user);
        return "passwordReset";
    }

    // form to set new password
    @PostMapping("/newPassword")
    public String newPassword(@RequestParam String newPassword, Model model, SessionStatus sessionStatus) {
        User user = (User) model.getAttribute("user");

        if (user != null && newPassword.length() >= 6) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user);
            sessionStatus.setComplete();
            return "redirect:/auth?success=passwordChanged";
        }
        return "error";
    }
}
