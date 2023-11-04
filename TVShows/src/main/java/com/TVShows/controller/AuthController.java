package com.TVShows.controller;

import com.TVShows.DTO.RegisterRequest;
import com.TVShows.service.AuthService;
import com.TVShows.service.ConfirmationTokenService;
import com.mailjet.client.errors.MailjetException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {	

	private final AuthService authService;
	private final ConfirmationTokenService tokenService;

	@PostMapping("/register")
	public String register(@ModelAttribute RegisterRequest request, Model model) throws MailjetException {
		model.addAttribute("user", request);
		authService.register(request);
		return "verification";
	}

	@GetMapping("/confirm")
	public String confirm(@RequestParam("token") String token) {
		tokenService.confirmToken(token);
		return "success";
	}
}
