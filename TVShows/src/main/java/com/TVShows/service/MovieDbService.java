package com.TVShows.service;

import com.TVShows.DataInitializers.LocalDateTimeAdapter;
import com.TVShows.apiResponse.ShowResponse;
import com.TVShows.domain.*;
import com.TVShows.exceptions.ShowAlreadyExistsException;
import com.TVShows.exceptions.ShowNotFoundException;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDbService {
    private final TVShowService showService;
    private final SeasonService seasonService;
    private final OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().add(new LocalDateTimeAdapter()).build();
    private final JsonAdapter<ShowResponse> adapter = moshi.adapter(ShowResponse.class);
    private final Logger logger = LoggerFactory.getLogger(MovieDbService.class);

    @Value("${themoviedb.token}")
    private String token;

    public TVShow createShowWithId(int id) {
        Request request = createRequest("https://api.themoviedb.org/3/tv/" + id + "?language=en-US", token);

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            ShowResponse showResponse = adapter.fromJson(response.body().source());
            if (showResponse != null) {
                return setValuesAndSave(showResponse);
            } else {
                throw new ShowNotFoundException("TV show with ID " + id + " not found");
            }
        } catch (IOException e) {
            logger.error("Error while creating a show with ID " + id, e);
        }
        return null;
    }

    public Request createRequest(String url, String token) {
        return new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", token)
                .build();
    }

    public TVShow setValuesAndSave(ShowResponse showResponse) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        TVShow show = new TVShow();
        if (showService.findShowByName(showResponse.getName()).isPresent()) {
            throw new ShowAlreadyExistsException("Show " + showResponse.getName() + " already exists.");
        } else {
            try {
                List<Genre> genres = showResponse.getGenres();
                List<Author> authors = showResponse.getCreated_by();
                List<Network> networks = showResponse.getNetworks();
                List<Season> seasons = showResponse.getSeasons();
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
                show.setNetworks(networks);
                show.setAuthors(authors);
                show.setGenre(genres);

                showService.createShow(show);

                for (Season season : seasons) {
                    season.setTvShow(show);
                    seasonService.save(season);
                }
            } catch (Exception e) {
                logger.error("Error while setting values from response", e);
            }

            return show;
        }
    }
}