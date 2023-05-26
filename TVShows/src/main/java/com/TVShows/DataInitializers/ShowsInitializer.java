package com.TVShows.DataInitializers;

import com.TVShows.apiResponse.TVShowResponse;
import com.TVShows.domain.Genre;
import com.TVShows.domain.TVShow;
import com.TVShows.enums.ShowStatus;
import com.TVShows.service.GenreService;
import com.TVShows.service.TVShowService;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowsInitializer implements CommandLineRunner {

    private final TVShowService showService;
    private final GenreService genreService;
    private final OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<TVShowResponse> adapter = moshi.adapter(TVShowResponse.class);

    @Override
    public void run(String... args) throws Exception {

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/tv/top_rated?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNmMzOGM1NDlkZTM2YTNlOWZmYTQ5YzYyN2IzMTU5NSIsInN1YiI6IjY0NjkxZDlkMDA2YjAxMDEwNThhMzU3MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.x7CRoCF6kNGo6l0w5DHYNGwl5eYhIlKzlHs7T9pN7R0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            TVShowResponse showResponse = adapter.fromJson(response.body().source());
            List<TVShowResponse.TVShowResponseResult> results = showResponse.getResults();
            if (results != null) {
                results.forEach(result -> {
                    TVShow show = new TVShow();
                    List<Genre> genres = genreService.mapIdsToGenres(result.getGenre_ids());

                    show.setStatus(ShowStatus.AIRING);
                    show.setName(result.getName());
                    show.setEpisodesNumber(10); // TODO: - IMPLEMENT SEASONS AND EPISODE NUMBERS FETCHING
                    show.setDescription(result.getOverview());
                    show.setImageUrl("https://image.tmdb.org/t/p/original" + result.getPoster_path());
                    show.setReleaseDate(result.getFirst_air_date());
                    show.setOriginCountry(result.getOrigin_country().toString().replace("[", "").replace("]", ""));
                    show.setOriginalName(result.getOriginal_name());
                    show.setVoteAverage(result.getVote_average());
                    show.setVoteCount(result.getVote_count());
                    show.setGenre(genres);
                    showService.createShow(show);
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

