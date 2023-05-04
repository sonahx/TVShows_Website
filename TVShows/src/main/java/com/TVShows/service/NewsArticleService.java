package com.TVShows.service;

import com.TVShows.domain.NewsArticle;
import com.TVShows.domain.TVShow;
import com.TVShows.repo.NewsArticleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsArticleService {

    private final NewsArticleRepo newsArticleRepo;

    public Optional<NewsArticle> findById(Long id){
        return newsArticleRepo.findById(id);
    }

    public NewsArticle save(NewsArticle newsArticle){
        return newsArticleRepo.save(newsArticle);
    }

    public Page<NewsArticle> findAllShowsWithPagination(int page, int size) {
        return newsArticleRepo.findAll(PageRequest.of(page,size));
    }

    public NewsArticle update(NewsArticle newsArticle){
        return newsArticleRepo.save(newsArticle);
    }
}
