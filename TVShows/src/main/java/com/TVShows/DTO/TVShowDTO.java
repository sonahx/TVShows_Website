package com.TVShows.DTO;

import com.TVShows.domain.ShowComment;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.enums.Genre;
import com.TVShows.enums.ShowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public class TVShowDTO {

    private Long id;
    private String name;
    private Integer episodesNumber;
    private String releaseDate;
    private Genre genre;
    private String directors;
    private String description;
    private String imageUrl;
    private String trailerUrl;
    private String nextEpisode;
    private ShowStatus status;
    private String episodeDuration;
    private String actors;
}
