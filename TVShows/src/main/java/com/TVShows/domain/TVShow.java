package com.TVShows.domain;

import com.TVShows.enums.ShowStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TVShow {
    private Integer id;
    private String name;
    private String origin_country;
    private String originalName;
    private String releaseDate;
    private String voteAverage;
    private Integer voteCount;
    private ShowStatus status;
    private String imageUrl;
    private String show_status;
    private Boolean adult;
    private String homepage;
    private Boolean in_production;
    private List<String> languages;
    private String last_air_date;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private String trailerUrl;
    private Double popularity;
    private String overview;
    private List<Genre> genre;
    private List<Author> authors = new ArrayList<>();
    private List<Network> networks = new ArrayList<>();
    private List<Season> seasons = new ArrayList<>();
}
