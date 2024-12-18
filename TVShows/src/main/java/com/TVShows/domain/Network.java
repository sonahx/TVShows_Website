package com.TVShows.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Network {
    private Integer id;
    private String name;
    private String origin_country;
    private List<TVShow> shows = new ArrayList<>();
}
