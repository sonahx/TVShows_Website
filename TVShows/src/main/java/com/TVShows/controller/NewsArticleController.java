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
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;
    private final NewsArticleCommentService newsArticleCommentService;

    @GetMapping("/{id}")
    public String newsArticle(@PathVariable int id, Model model) {
        NewsArticle newsArticle = newsArticleService.findById(id).orElse(null);

        if (newsArticle != null) {
            model.addAttribute("NewsArticle", newsArticle);
            model.addAttribute("NewsArticleComment", new NewsArticleComment());
            model.addAttribute("Comments", newsArticleCommentService.findAllByNewsArticleId(id));
            return "newsArticle";
        }
        return "error";
    }

    @PostMapping("/create")
    public String createNewsArticle(@ModelAttribute NewsArticle newsArticle, Model model) {
        User user = (User) model.getAttribute("authenticatedUser");

        if (newsArticle != null && user != null) {
            NewsArticle createArticle = new NewsArticle(newsArticle.getName(), newsArticle.getRelatedTo(),
                    user, newsArticle.getText(), LocalDateTime.now(), newsArticle.getImageUrl());
            newsArticleService.save(createArticle);
        }
        return "redirect:/home";
    }

    @PostMapping("/{id}/comment")
    public String comment(@PathVariable("id") int id, Model model,
                          @ModelAttribute("NewsArticleComment") NewsArticleComment text) {
        User user = (User) model.getAttribute("authenticatedUser");
        NewsArticle article = newsArticleService.findById(id).orElse(null);

        if (article != null && user != null && !text.getText().trim().isEmpty()) {
            NewsArticleComment comment = new NewsArticleComment(text.getText(), user, LocalDateTime.now(), article);
            newsArticleCommentService.save(comment);
            return "redirect:/news/" + id;
        }
        return "error";
    }

    @PostMapping("/{articleId}/comment/delete/{commentId}")
    public String removeComment(@PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, Model model) {
        NewsArticleComment comment = newsArticleCommentService.findById(commentId);
        Optional<NewsArticle> article = newsArticleService.findById(articleId);
        User user = (User) model.getAttribute("authenticatedUser");

        if (article.isPresent() && user != null && Objects.equals(user.getId(), comment.getAuthor().getId())) {
            newsArticleCommentService.delete(commentId);
            article.get().getComments().remove(comment);
            newsArticleService.update(article.get());
            return "redirect:/news/" + articleId;
        }
        return "error";
    }
}
