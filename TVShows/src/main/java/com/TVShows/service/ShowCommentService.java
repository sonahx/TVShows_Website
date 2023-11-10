package com.TVShows.service;

import com.TVShows.domain.ShowComment;
import com.TVShows.repo.ShowCommentRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowCommentService {

    private final ShowCommentRepo repo;
    private final Logger logger = LoggerFactory.getLogger(ShowCommentService.class);

    public ShowComment save(ShowComment comment) {
        logger.info("Creating a new comment for show: {}", comment.getTvShow().getName());
        return repo.save(comment);
    }

    public ShowComment findShowCommentById(long id) {
        return repo.findShowCommentById(id);
    }

    public List<ShowComment> findAllCommentsByShowId(Long id) {
        return repo.findAllShowCommentsByTvShowId(id);
    }

    public void delete(long id) {
        logger.info("Removing comment {}", id);
        repo.deleteById(id);
    }
}
