package com.TVShows.service;

import com.TVShows.domain.ShowComment;
import com.TVShows.repo.ShowCommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowCommentService {

    private final ShowCommentRepo repo;

    public ShowComment save(ShowComment comment){
        return repo.save(comment);
    }

    public List<ShowComment> findAllCommentsByShowId(Long id){
        return repo.findAllShowCommentsByTvShowId(id);
    }
}
