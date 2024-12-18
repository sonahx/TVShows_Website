package com.TVShows.service;

import com.TVShows.DTO.ResponseParser;
import com.TVShows.config.LocalDateTimeAdapter;
import com.TVShows.domain.Season;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiResponseSeasonService {
    @Value("${themoviedb.token}")
    private String token;
    private final OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().add(new LocalDateTimeAdapter()).build();
    private final JsonAdapter<Season> adapter = moshi.adapter(Season.class);

    public Season sendRequest(String url) {
        Season resultResponse = null;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("Failed to fetch data: " + response.message());
            }

            Season season = adapter.fromJson(response.body().source());
            if (season != null) {
                resultResponse = season;
            }
        } catch (IOException e) {
            System.err.println("Error processing: " + e.getMessage());
        }
        return ResponseParser.parseResponseToSeason(resultResponse);
    }
}

