package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import com.TVShows.domain.User;
import com.TVShows.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/{id}/image/upload")
    public void uploadImage(@ModelAttribute("image") ImageEncoder image, @PathVariable int id,
                            Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        User authenticatedUser = (User) model.getAttribute("authenticatedUser");

        if (authenticatedUser != null && authenticatedUser.getId() == (id)) {
            User toUpdate = image.encodeImage(authenticatedUser);
            userService.updateUser(toUpdate);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Model model) {
        User authenticatedUser = (User) model.getAttribute("authenticatedUser");

        if (authenticatedUser != null) {
            userService.removeUser(authenticatedUser);
        }
        return "redirect:/logout";
    }
}
