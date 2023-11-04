package com.TVShows.controller;

import com.TVShows.DTO.TVShowDTO;
import com.TVShows.domain.TvShow;
import com.TVShows.service.TVShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchRestController {

    private final TVShowService showService;

    @GetMapping(value = "/{keyword}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<TVShowDTO>> findShowsByKeyword(@PathVariable String keyword) {
        try {
            List<TvShow> shows = showService.findShowsByKeyword(keyword);
            List<TVShowDTO> tvShowDTOs = shows.stream()
                    .map(tvShow -> {
                        TVShowDTO tvShowDto = new TVShowDTO();
                        BeanUtils.copyProperties(tvShow, tvShowDto);
                        return tvShowDto;
                    })
                    .sorted(Comparator.comparing(TVShowDTO::getName))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(tvShowDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}