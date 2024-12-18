package com.TVShows.service;


import com.TVShows.domain.ShowComment;
import com.TVShows.domain.User;
import com.TVShows.repo.ShowCommentRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowCommentServiceTest {

    @Mock
    private ShowCommentRepo repo;
    @InjectMocks
    private ShowCommentService service;

    @Test
    @DisplayName("Test saving a comment")
    public void shouldSaveComment() {
        // given
        ShowComment comment = new ShowComment("This is a test comment", getUser(), getShowId(), LocalDateTime.now());

        // when
        when(repo.save(comment)).thenReturn(comment);
        ShowComment savedComment = service.save(comment);

        // then
        verify(repo).save(comment);
        assertEquals(comment, savedComment);
    }

    @Test
    @DisplayName("Test finding all comments by show id")
    public void shouldFindAllCommentsByShowId() {
        // given
        int showId = 1;
        ShowComment comment1 = new ShowComment("This is a test comment", getUser(), getShowId(), LocalDateTime.now());
        ShowComment comment2 = new ShowComment("This is a test comment 2", getUser(), getShowId(), LocalDateTime.now());

        List<ShowComment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        // when
        when(repo.findAllShowCommentsByShowId(showId)).thenReturn(comments);
        List<ShowComment> result = service.findAllCommentsByShowId(showId);

        // then
        verify(repo).findAllShowCommentsByShowId(showId);
        assertEquals(comments, result);
    }

    public User getUser() {
        return User.builder().id(1).name("JohnDoe").email("johndoe@example.com").password("pass").build();
    }

    public int getShowId() {
        return 1;
    }
}
