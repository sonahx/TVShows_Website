package com.TVShows.service;

import com.TVShows.domain.NewsArticle;
import com.TVShows.repo.NewsArticleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsArticleService {

    private final NewsArticleRepo newsArticleRepo;

    public Optional<NewsArticle> findById(int id) {
        return newsArticleRepo.findById(id);
    }

    public NewsArticle save(NewsArticle newsArticle) {
        log.info("Creating new news article: {}, tag: {}", newsArticle.getName(), newsArticle.getRelatedTo());
        return newsArticleRepo.save(newsArticle);
    }

    public Page<NewsArticle> findAllArticlesWithPagination(int page, int size) {
       log.info("Looking for all articles");
        return newsArticleRepo.findAll(PageRequest.of(page, size));
    }

    public NewsArticle update(NewsArticle newsArticle) {
        log.info("Updating article: {}", newsArticle.getName());
        return newsArticleRepo.save(newsArticle);
    }
}
