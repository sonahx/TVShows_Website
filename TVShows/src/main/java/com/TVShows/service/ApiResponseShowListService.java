package com.TVShows.service;

import com.TVShows.DTO.ResponseParser;
import com.TVShows.apiResponse.ShowListResponse;
import com.TVShows.config.LocalDateTimeAdapter;
import com.TVShows.apiResponse.ShowResponse;
import com.TVShows.domain.*;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiResponseShowListService {

    @Value("${themoviedb.token}")
    private String token;
    private final OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().add(new LocalDateTimeAdapter()).build();
    private final JsonAdapter<ShowListResponse> adapter = moshi.adapter(ShowListResponse.class);

    public List<TVShow> sendRequest(String url) {
        List<ShowResponse> listResult = new ArrayList<>();
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

            ShowListResponse showResponseList = adapter.fromJson(response.body().source());
            if (showResponseList != null) {
                listResult.addAll(showResponseList.getResults());
            }
        } catch (IOException e) {
            System.err.println("Error processing: " + e.getMessage());
        }
        return parseResponseListToShows(listResult);
    }

    public List<TVShow> parseResponseListToShows(List<ShowResponse> showResponseList) {
        List<TVShow> results = new ArrayList<>();

        for (ShowResponse showResponse : showResponseList) {
            results.add(ResponseParser.parseResponseToShow(showResponse));
        }
        return results;
    }
}


