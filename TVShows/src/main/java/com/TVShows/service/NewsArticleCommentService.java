package com.TVShows.service;

import com.TVShows.domain.NewsArticleComment;
import com.TVShows.repo.NewsArticleCommentRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsArticleCommentService {

    private final NewsArticleCommentRepo repo;
    private final Logger logger = LoggerFactory.getLogger(NewsArticleCommentService.class);

    public NewsArticleComment save(NewsArticleComment newsArticleComment) {
        logger.info("Creating a new comment for article: {}", newsArticleComment.getNewsArticle().getName());
        return repo.save(newsArticleComment);
    }

    public NewsArticleComment findById(Long id) {
        return repo.findNewsArticleCommentById(id);
    }

    public List<NewsArticleComment> findAllByNewsArticleId(Long id) {
        return repo.findAllByNewsArticleId(id);
    }

    public void delete(Long id) {
        logger.info("Deleting article comment with id: {}", id);
        repo.deleteById(id);
    }
}

