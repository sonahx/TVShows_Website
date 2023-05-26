package com.TVShows.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TVShowResponse {
    private int page;
    private List<TVShowResponseResult> results;
    private int total_pages;
    private int total_results;

    @Getter @Setter
    public static class TVShowResponseResult {
        private String name;
        private List<String> origin_country;
        private List<Long> genre_ids;
        private String original_language;
        private String original_name;
        private String overview;
        private Double popularity;
        private String poster_path;
        private Double vote_average;
        private Integer vote_count;
        private String backdrop_path;
        private String first_air_date;
    }
}