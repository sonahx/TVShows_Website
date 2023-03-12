package com.TVShows.service;

import com.TVShows.domain.*;
import com.TVShows.repo.UsersShowsRepo;
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
    private UsersShowsRepo repo;
    @InjectMocks
    private UsersShowsService service;
    @Captor
    private ArgumentCaptor<UsersShows> usersShowsCaptor;

    @Test
    @DisplayName("Test save method")
    void shouldSaveUsersShows() {
        // Given
        UsersShows usersShows = new UsersShows();
        usersShows.setUser(User.builder().username("JohnDoe").email("johndoe@example.com")
                .password("password").roles(Role.USER).build());
        usersShows.setTvShow(new TVShow("showname", "releasedate",
                Genre.DRAMA, "directors", "descs", "url",
                "nextepi", ShowStatus.AIRING, "duration", "actors"));
        usersShows.setStatus(ViewerStatus.WATCHING);

        // When
        service.save(usersShows);

        // Then
        verify(repo).save(usersShowsCaptor.capture());
        UsersShows savedUsersShows = usersShowsCaptor.getValue();
        assertEquals(usersShows, savedUsersShows);
    }

    @Test
    @DisplayName("Test findAll method")
    void shouldFindAllUsersShows() {
        // Given
        UsersShows usersShows1 = new UsersShows();
        usersShows1.setUser(User.builder().username("JohnDoe").email("johndoe@example.com")
                .password("password").roles(Role.USER).build());
        usersShows1.setTvShow(new TVShow("showname", "releasedate",
                Genre.DRAMA, "directors", "descs", "url",
                "nextepi", ShowStatus.AIRING, "duration", "actors"));
        usersShows1.setStatus(ViewerStatus.WATCHING);

        UsersShows usersShows2 = new UsersShows();
        usersShows2.setUser(User.builder().username("LanaDoe").email("lanadoe@example.com")
                .password("securepassword").roles(Role.USER).build());
        usersShows2.setTvShow(new TVShow("showname 2", "releasedate 2",
                Genre.DRAMA, "directors 2", "descs 2", "url 2",
                "nextepi 2", ShowStatus.AIRING, "duration 2", "actors 2"));
        usersShows2.setStatus(ViewerStatus.DROPPED);

        List<UsersShows> usersShowsList = Arrays.asList(usersShows1, usersShows2);

        // When
        when(repo.findAll()).thenReturn(usersShowsList);
        List<UsersShows> result = service.findAll();

        // Then
        assertEquals(usersShowsList, result);
        verify(repo).findAll();
    }
}