package com.TVShows.repo;

import com.TVShows.domain.NewsArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsArticleCommentRepo extends JpaRepository<NewsArticleComment, Long> {
}
