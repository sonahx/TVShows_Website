package com.TVShows.service;

import com.TVShows.domain.TVShow;
import com.TVShows.enums.ShowStatus;
import com.TVShows.repo.TVShowRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TVShowServiceTest {

    @Mock
    private TVShowRepo showRepo;
    @InjectMocks
    private TVShowService showService;
    @Captor
    private ArgumentCaptor<TVShow> showCaptor;

    @Test
    @DisplayName("Test creating show")
    public void shouldCreateShow() {
        // given
        TVShow show = createTVShow();

        // when
        showService.createShow(show);

        // then
        verify(showRepo, times(1)).save(showCaptor.capture());
        assertEquals(show, showCaptor.getValue());
    }

    @Test
    @DisplayName("Test finding show with id")
    public void shouldFindShowById() {
        // given
        TVShow show = createTVShow();
        when(showRepo.findById(1L)).thenReturn(Optional.of(show));

        // when
        Optional<TVShow> result = showService.findShowById(1L);

        // then
        assertTrue(result.isPresent());
        assertEquals(show, result.get());
    }

    @Test
    @DisplayName("Test finding show by name")
    public void shouldFindShowByName() {
        // given
        TVShow show = createTVShow();
        when(showRepo.findShowByName("Breaking Bad")).thenReturn(Optional.of(show));

        // when
        Optional<TVShow> result = showService.findShowByName("Breaking Bad");

        // then
        assertEquals(show, result.get());
    }

    @Test
    @DisplayName("Test finding all shows")
    public void shouldFindAllShows() {
        // given
        List<TVShow> shows = createTVShowList();

        // when
        when(showRepo.findAll()).thenReturn(shows);
        List<TVShow> result = showService.findAllShows();

        // then
        assertEquals(shows, result);
    }

    private TVShow createTVShow() {
        TVShow show = new TVShow();
        show.setName("Breaking Bad");
        show.setReleaseDate("date");
        show.setDirectors("Vince Gilligan");
        show.setDescription("desc");
        show.setImageUrl("jpeg");
        show.setNextEpisode("12.02");
        show.setStatus(ShowStatus.AIRING);
        show.setEpisodeDuration("60m");
        show.setActors("some actors");
        return show;
    }

    private List<TVShow> createTVShowList() {
        return List.of(
                createTVShow(),
                createTVShow()
        );
    }
}

