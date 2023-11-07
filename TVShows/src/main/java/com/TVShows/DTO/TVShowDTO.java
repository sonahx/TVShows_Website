package com.TVShows.DTO;

import com.TVShows.domain.Genre;
import com.TVShows.enums.ShowStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TVShowDTO {
    private Long id;
    private String name;
    private Integer episodesNumber;
    private String releaseDate;
    private String directors;
    private String description;
    private String imageUrl;
    private String trailerUrl;
    private String nextEpisode;
    private String episodeDuration;
    private String actors;

    @JsonIgnore
    private List<Genre> genre;

    @JsonIgnore
    private ShowStatus status;
}
