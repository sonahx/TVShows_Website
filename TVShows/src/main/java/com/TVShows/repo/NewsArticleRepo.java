package com.TVShows.repo;

import com.TVShows.domain.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsArticleRepo extends JpaRepository<NewsArticle, Integer> {
    Optional<NewsArticle> findNewsArticleByName (String name);
}
