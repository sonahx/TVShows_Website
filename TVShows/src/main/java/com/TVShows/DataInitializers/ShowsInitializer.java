//package com.TVShows.DataInitializers;
//
//import com.TVShows.apiResponse.ShowResponse;
//import com.TVShows.domain.*;
//import com.TVShows.service.SeasonService;
//import com.TVShows.service.TVShowService;
//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//import lombok.RequiredArgsConstructor;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class ShowsInitializer implements CommandLineRunner {
//
//    private final TVShowService showService;
//    private final SeasonService seasonService;
//    private final OkHttpClient client = new OkHttpClient();
//    private final Moshi moshi = new Moshi.Builder().add(new LocalDateTimeAdapter()).build();
//    private final JsonAdapter<ShowResponse> adapter = moshi.adapter(ShowResponse.class);
//
//    @Value("${themoviedb.token}")
//    private String token;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        int[] ids = new int[]{131378, 1396, 94605, 37854, 60625, 70785, 31911, 246,
//                85937, 95557, 124834, 65930, 1429, 100088, 92685, 87108, 60059, 72637};
//
//        for (int i = 1; i < ids.length; i++) {
//            Request request = new Request.Builder()
//
//                    .url("https://api.themoviedb.org/3/tv/" + ids[i] + "?language=en-US")
//                    .get()
//                    .addHeader("accept", "application/json")
//                    .addHeader("Authorization", token)
//                    .build();
//
//            try (Response response = client.newCall(request).execute()) {
//                if (!response.isSuccessful()) {
//                    throw new IOException("Unexpected response code: " + response);
//                }
//
//                ShowResponse showResponse = adapter.fromJson(response.body().source());
//                if (showResponse != null) {
//
//                    DecimalFormat decimalFormat = new DecimalFormat("#.0");
//
//                    TVShow show = new TVShow();
//                    List<Genre> genres = showResponse.getGenres();
//                    List<Author> authors = showResponse.getCreated_by();
//                    List<Network> networks = showResponse.getNetworks();
//                    List<Season> seasons = showResponse.getSeasons();
//                    show.setOverview(showResponse.getOverview());
//                    show.setName(showResponse.getName());
//                    show.setNumber_of_episodes(showResponse.getNumber_of_episodes());
//                    show.setNumber_of_seasons(showResponse.getNumber_of_seasons());
//                    show.setImageUrl("https://image.tmdb.org/t/p/original" + showResponse.getPoster_path());
//                    show.setReleaseDate(showResponse.getFirst_air_date());
//                    show.setOrigin_country(showResponse.getOrigin_country().toString().substring(1, showResponse.getOrigin_country().toString().length() - 1));
//                    show.setOriginalName(showResponse.getOriginal_name());
//                    show.setVoteAverage(decimalFormat.format(showResponse.getVote_average()));
//                    show.setVoteCount(showResponse.getVote_count());
//                    show.setAdult(showResponse.getAdult());
//                    show.setHomepage(showResponse.getHomepage());
//                    show.setIn_production(showResponse.getIn_production());
//                    show.setLanguages(showResponse.getLanguages());
//                    show.setLast_air_date(showResponse.getLast_air_date());
//                    show.setShow_status(showResponse.getStatus());
//                    show.setNetworks(networks);
//                    show.setAuthors(authors);
//                    show.setGenre(genres);
//
//                    showService.createShow(show);
//
//                    for (Season season : seasons) {
//                        season.setTvShow(show);
//                        seasonService.save(season);
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
//
//
