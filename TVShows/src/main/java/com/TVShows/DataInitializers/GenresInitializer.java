//package com.TVShows.DataInitializers;
//
//import com.TVShows.apiResponse.GenreResponse;
//import com.TVShows.domain.Genre;
//import com.TVShows.service.GenreService;
//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//import lombok.RequiredArgsConstructor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class GenresInitializer implements CommandLineRunner {
//
//    private final GenreService genreService;
//    private final OkHttpClient client = new OkHttpClient();
//    private final Moshi moshi = new Moshi.Builder().build();
//    private final JsonAdapter<GenreResponse> adapter = moshi.adapter(GenreResponse.class);
//
//    @Value("${themoviedb.token}")
//    private String token;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Request request = new Request.Builder()
//                .url("https://api.themoviedb.org/3/genre/tv/list?language=en")
//                .get()
//                .addHeader("accept", "application/json")
//                .addHeader("Authorization", token)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected response code: " + response);
//            }
//
//            GenreResponse genreResponse = adapter.fromJson(response.body().source());
//            System.out.println(response.body().toString());
//            List<GenreResponse.GenreResponseResult> results = genreResponse.getGenres();
//            if (results != null) {
//                results.forEach(result -> {
//                    Genre genre = new Genre();
//                    genre.setId(result.getId());
//                    genre.setName(result.getName());
//                    genreService.save(genre);
//                });
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
