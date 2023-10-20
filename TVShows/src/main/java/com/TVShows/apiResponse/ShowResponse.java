package com.TVShows.apiResponse;

import com.TVShows.domain.Author;
import com.TVShows.domain.Genre;
import com.TVShows.domain.Network;
import com.TVShows.domain.Season;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ShowResponse {
    private Integer id;
    private Boolean adult;
    private String backdrop_path;
    private List<Author> created_by;
    private String first_air_date;
    private List<Genre> genres;
    private String homepage;
    private Boolean in_production;
    private List<String> languages;
    private String last_air_date;
    private String name;
    private List<Network> networks;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private List<String> origin_country;
    private String overview;
    private List<Season> seasons;
    private String status;
    private Double vote_average;
    private Integer vote_count;
    private String original_name;
}
