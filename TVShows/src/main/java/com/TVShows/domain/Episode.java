package com.TVShows.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Episode {
    Integer id;
    String air_date;
    Integer episode_number;
    String episode_type;
    String name;
    String overview;
    String production_code;
    Integer runtime;
    Integer season_number;
    Integer show_id;
    Double vote_average;
    Integer vote_count;
}
