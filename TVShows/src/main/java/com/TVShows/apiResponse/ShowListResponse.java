package com.TVShows.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShowListResponse {

    private int page;
    private List<ShowResponse> results = new ArrayList<>();
}
