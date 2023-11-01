package com.TVShows.controller;

import com.TVShows.DTO.ImageEncoder;
import com.TVShows.domain.User;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    public void getUserInfo(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);

        model.addAttribute("user", user);
    }

    @PostMapping("/{id}/image/upload")
    public void uploadImage(@ModelAttribute("image") ImageEncoder image, @PathVariable Long id,
                            Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        User authenticatedUser = (User) model.getAttribute("authenticatedUser");

        if (authenticatedUser != null && authenticatedUser.getId().equals(id)) {
            User toUpdate = image.encodeImage(authenticatedUser);
            userService.updateUser(toUpdate);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
