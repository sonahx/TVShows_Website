package com.TVShows.service;

import com.TVShows.domain.NewsArticleComment;
import com.TVShows.repo.NewsArticleCommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsArticleCommentService {

    private final NewsArticleCommentRepo newsArticleCommentRepo;

    public NewsArticleComment save(NewsArticleComment newsArticleComment){
        return newsArticleCommentRepo.save(newsArticleComment);
    }
}

