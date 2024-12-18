package com.TVShows.service;

import com.TVShows.domain.NewsArticleComment;
import com.TVShows.repo.NewsArticleCommentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsArticleCommentService {

    private final NewsArticleCommentRepo repo;

    public NewsArticleComment save(NewsArticleComment newsArticleComment) {
        log.info("Creating a new comment for article: {}", newsArticleComment.getNewsArticle().getName());
        return repo.save(newsArticleComment);
    }

    public NewsArticleComment findById(int id) {
        return repo.findNewsArticleCommentById(id);
    }

    public List<NewsArticleComment> findAllByNewsArticleId(int id) {
        return repo.findAllByNewsArticleId(id);
    }

    public void delete(int id) {
        log.info("Deleting article comment with id: {}", id);
        repo.deleteById(id);
    }
}

