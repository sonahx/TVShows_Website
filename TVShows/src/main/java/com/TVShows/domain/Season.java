package com.TVShows.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    private Integer id;
    private String name;
    private Integer episode_count;
    private Integer season_number;
    private Double vote_average;
    private String poster_path;
    private Integer showId;
    private List<Episode> episodes = new ArrayList<>();
}
