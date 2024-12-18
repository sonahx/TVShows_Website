package com.TVShows.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Genre {
    private Long id;
    private String name;
    private List<TVShow> shows;
}
