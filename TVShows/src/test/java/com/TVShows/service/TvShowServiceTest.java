package com.TVShows.service;

import com.TVShows.domain.TvShow;
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
public class TvShowServiceTest {

    @Mock
    private TVShowRepo showRepo;
    @InjectMocks
    private TVShowService showService;
    @Captor
    private ArgumentCaptor<TvShow> showCaptor;

    @Test
    @DisplayName("Test creating show")
    public void shouldCreateShow() {
        // given
        TvShow show = createTVShow();

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
        TvShow show = createTVShow();
        when(showRepo.findById(1L)).thenReturn(Optional.of(show));

        // when
        Optional<TvShow> result = showService.findShowById(1L);

        // then
        assertTrue(result.isPresent());
        assertEquals(show, result.get());
    }

    @Test
    @DisplayName("Test finding show by name")
    public void shouldFindShowByName() {
        // given
        TvShow show = createTVShow();
        when(showRepo.findShowByName("Breaking Bad")).thenReturn(Optional.of(show));

        // when
        Optional<TvShow> result = showService.findShowByName("Breaking Bad");

        // then
        assertEquals(show, result.get());
    }

    @Test
    @DisplayName("Test finding all shows")
    public void shouldFindAllShows() {
        // given
        List<TvShow> shows = createTVShowList();

        // when
        when(showRepo.findAll()).thenReturn(shows);
        List<TvShow> result = showService.findAllShows();

        // then
        assertEquals(shows, result);
    }

    private TvShow createTVShow() {
        TvShow show = new TvShow();
        show.setName("Breaking Bad");
        show.setReleaseDate("date");
        show.setOverview("desc");
        show.setImageUrl("jpeg");
        show.setLast_air_date("12.02");
        show.setStatus(ShowStatus.AIRING);
        return show;
    }

    private List<TvShow> createTVShowList() {
        return List.of(
                createTVShow(),
                createTVShow()
        );
    }
}

