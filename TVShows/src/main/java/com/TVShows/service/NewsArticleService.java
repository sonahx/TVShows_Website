package com.TVShows.service;

import com.TVShows.domain.NewsArticle;
import com.TVShows.repo.NewsArticleRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsArticleService {

    private final NewsArticleRepo newsArticleRepo;
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public Optional<NewsArticle> findById(Long id){
        return newsArticleRepo.findById(id);
    }

    public NewsArticle save(NewsArticle newsArticle){
        logger.info("Creating new News Article: {}, tag: {}", newsArticle.getName(), newsArticle.getRelatedTo());
        return newsArticleRepo.save(newsArticle);
    }

    public Page<NewsArticle> findAllShowsWithPagination(int page, int size) {
        return newsArticleRepo.findAll(PageRequest.of(page,size));
    }

    public NewsArticle update(NewsArticle newsArticle){
        return newsArticleRepo.save(newsArticle);
    }
}
