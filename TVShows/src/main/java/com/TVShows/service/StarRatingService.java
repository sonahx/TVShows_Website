package com.TVShows.service;

import com.TVShows.domain.StarRating;
import org.springframework.stereotype.Service;

@Service
public class StarRatingService {

    public StarRating calculateStarRating(double voteAverage) {
        int fullStars = (int) voteAverage;
        int halfStars = (voteAverage - fullStars >= 0.5) ? 1 : 0;
        int emptyStars = 10 - fullStars - halfStars;
        return new StarRating(fullStars, halfStars, emptyStars);
    }
}
