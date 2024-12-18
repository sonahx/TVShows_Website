package com.TVShows.repo;

import com.TVShows.domain.ShowComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowCommentRepo extends JpaRepository<ShowComment, Integer>{

    List<ShowComment> findAllShowCommentsByShowId(int showId);

    ShowComment findShowCommentById(int id);
}
