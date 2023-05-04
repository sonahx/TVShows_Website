package com.TVShows.DTO;

import com.TVShows.enums.Genre;
import com.TVShows.enums.ShowStatus;
import lombok.Getter;
import lombok.Setter;

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
