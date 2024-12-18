package com.TVShows.DTO;

import com.TVShows.apiResponse.ShowResponse;
import com.TVShows.domain.*;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class ResponseParser {

    public static TVShow parseResponseToShow(ShowResponse showResponse) {
        if (showResponse == null) {
            throw new IllegalArgumentException("ShowResponse cannot be null");
        }

        TVShow show = new TVShow();
        DecimalFormat decimalFormat = new DecimalFormat("#.0");

        List<Genre> genres = showResponse.getGenres();
        List<Author> authors = showResponse.getCreated_by();
        List<Network> networks = showResponse.getNetworks();
        List<Season> seasons = showResponse.getSeasons();
        show.setId(showResponse.getId());
        show.setOverview(showResponse.getOverview());
        show.setName(showResponse.getName());
        show.setNumber_of_episodes(showResponse.getNumber_of_episodes());
        show.setNumber_of_seasons(showResponse.getNumber_of_seasons());
        show.setImageUrl("https://image.tmdb.org/t/p/original" + showResponse.getPoster_path());
        show.setReleaseDate(showResponse.getFirst_air_date());
        show.setOrigin_country(showResponse.getOrigin_country().toString().substring(1, showResponse.getOrigin_country().toString().length() - 1));
        show.setOriginalName(showResponse.getOriginal_name());
        show.setVoteAverage(decimalFormat.format(showResponse.getVote_average()));
        show.setVoteCount(showResponse.getVote_count());
        show.setAdult(showResponse.getAdult());
        show.setHomepage(showResponse.getHomepage());
        show.setIn_production(showResponse.getIn_production());
        show.setLanguages(showResponse.getLanguages());
        show.setLast_air_date(showResponse.getLast_air_date());
        show.setShow_status(showResponse.getStatus());
        show.setPopularity(showResponse.getPopularity());
        show.setNetworks(networks);
        show.setAuthors(authors);
        show.setGenre(genres);
        show.setSeasons(seasons);
        return show;
    }

    public static Season parseResponseToSeason(Season seasonResponse) {
        if (seasonResponse == null) {
            throw new IllegalArgumentException("SeasonResponse cannot be null");
        }
        Season season = new Season();
        season.setId(seasonResponse.getId());
        season.setName(seasonResponse.getName());
        season.setSeason_number(seasonResponse.getSeason_number());
        season.setVote_average(seasonResponse.getVote_average());
        season.setPoster_path(seasonResponse.getPoster_path());
        season.setShowId(seasonResponse.getShowId());
        season.setEpisodes(seasonResponse.getEpisodes());
        season.setEpisode_count(seasonResponse.getEpisodes().size());
        return season;
    }
}
