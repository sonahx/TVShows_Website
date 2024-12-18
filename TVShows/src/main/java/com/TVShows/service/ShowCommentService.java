package com.TVShows.service;

import com.TVShows.domain.ShowComment;
import com.TVShows.repo.ShowCommentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShowCommentService {

    private final ShowCommentRepo repo;

    public ShowComment save(ShowComment comment) {
        log.info("Creating a new comment for show: {}", comment.getShowId());
        return repo.save(comment);
    }

    public ShowComment findShowCommentById(int id) {
        log.info("Looking for show comment with id: {}", id);
        return repo.findShowCommentById(id);
    }

    public List<ShowComment> findAllCommentsByShowId(int id) {
        log.info("Looking for show comments by id: {}", id);
        return repo.findAllShowCommentsByShowId(id);
    }

    public void delete(int id) {
        log.info("Removing comment {}", id);
        repo.deleteById(id);
    }
}
