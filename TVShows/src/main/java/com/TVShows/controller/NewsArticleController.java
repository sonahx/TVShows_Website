package com.TVShows.controller;

import com.TVShows.domain.NewsArticle;
import com.TVShows.domain.NewsArticleComment;
import com.TVShows.domain.User;
import com.TVShows.service.NewsArticleCommentService;
import com.TVShows.service.NewsArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;
    private final NewsArticleCommentService newsArticleCommentService;

    @GetMapping("/{id}")
    public String newsArticle(@PathVariable Long id, Model model) {
        newsArticleService.findById(id).ifPresent(newsArticle -> {
            model.addAttribute("NewsArticle", newsArticle);
            model.addAttribute("NewsArticleComment", new NewsArticleComment());
        });
        return "newsArticle";
    }

    @PostMapping("/{id}/comment")
    public String comment(@PathVariable("id") Long id, Model model,
                             @ModelAttribute("NewsArticleComment") NewsArticleComment text) {
        User user = (User) model.getAttribute("authenticatedUser");
        NewsArticle article = newsArticleService.findById(id).orElse(null);

        if (article != null && user != null && text.getText().trim().length() >= 1) {
            //create and save comment
            NewsArticleComment comment = new NewsArticleComment();
            comment.setText(text.getText());
            comment.setAuthor(user);
            comment.setNewsArticle(article);
            comment.setDate(LocalDateTime.now());
            newsArticleCommentService.save(comment);

            //update TVShow
            List<NewsArticleComment> currentComments = article.getComments();
            currentComments.add(comment);
            article.setComments(currentComments);
            newsArticleService.update(article);
        }
        return "redirect:/news/" + id;
    }
}
