package com.TVShows.repo;

import com.TVShows.domain.NewsArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsArticleCommentRepo extends JpaRepository<NewsArticleComment, Long> {

    NewsArticleComment findNewsArticleCommentById(Long id);

    List<NewsArticleComment> findAllByNewsArticleId(Long id);
}
