package com.TVShows.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class Author {
    private Integer id;
    private String name;
    private Integer gender;
    private List<TVShow> shows = new ArrayList<>();
}
