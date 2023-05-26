package com.TVShows.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GenreResponse {
    private List<GenreResponseResult> genres;

    @Getter @Setter
    public static class GenreResponseResult{
        private Long id;
        private String name;
    }
}


