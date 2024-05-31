package com.TVShows.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StarRating {
    private int fullStars;
    private int halfStars;
    private int emptyStars;
}
