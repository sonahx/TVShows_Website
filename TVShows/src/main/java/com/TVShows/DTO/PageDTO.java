package com.TVShows.DTO;

import com.TVShows.domain.TVShow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PageDTO {
    private int number;
    private int totalPages;
    private List<TVShow> content;
}
