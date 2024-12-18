package com.TVShows.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StarRating {
    private int fullStars;
    private int halfStars;
    private int emptyStars;
}
