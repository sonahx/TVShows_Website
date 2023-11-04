package com.TVShows.service;

import com.TVShows.domain.TvShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.Role;
import com.TVShows.enums.ShowStatus;
import com.TVShows.enums.ViewerStatus;
import com.TVShows.repo.UsersShowProgressRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersShowsServiceTest {

    @Mock
    private UsersShowProgressRepo repo;
    @InjectMocks
    private UsersShowProgressService service;
    @Captor
    private ArgumentCaptor<UsersShowProgress> usersShowsCaptor;

    @Test
    @DisplayName("Test save method")
    void shouldSaveUsersShows() {
        // Given
        UsersShowProgress usersShows = new UsersShowProgress();
        usersShows.setUser(User.builder().name("JohnDoe").email("johndoe@example.com")
                .password("password").roles(Role.USER).build());
        usersShows.setTvShow(createTVShow());
        usersShows.setStatus(ViewerStatus.WATCHING);

        // When
        service.save(usersShows);

        // Then
        verify(repo).save(usersShowsCaptor.capture());
        UsersShowProgress savedUsersShows = usersShowsCaptor.getValue();
        assertEquals(usersShows, savedUsersShows);
    }

    @Test
    @DisplayName("Test findAll method")
    void shouldFindAllUsersShows() {
        // Given
        UsersShowProgress usersShows1 = new UsersShowProgress();
        usersShows1.setUser(User.builder().name("JohnDoe").email("johndoe@example.com")
                .password("password").roles(Role.USER).build());
        usersShows1.setTvShow(createTVShow());
        usersShows1.setStatus(ViewerStatus.WATCHING);

        UsersShowProgress usersShows2 = new UsersShowProgress();
        usersShows2.setUser(User.builder().name("LanaDoe").email("lanadoe@example.com")
                .password("securepassword").roles(Role.USER).build());
        usersShows2.setTvShow(createTVShow());
        usersShows2.setStatus(ViewerStatus.DROPPED);

        List<UsersShowProgress> usersShowsList = Arrays.asList(usersShows1, usersShows2);

        // When
        when(repo.findAll()).thenReturn(usersShowsList);
        List<UsersShowProgress> result = service.findAll();

        // Then
        assertEquals(usersShowsList, result);
        verify(repo).findAll();
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
}