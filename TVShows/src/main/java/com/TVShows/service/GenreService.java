package com.TVShows.service;

import com.TVShows.domain.Genre;
import com.TVShows.repo.GenreRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

        private final GenreRepo genreRepo;
        private final Logger logger = LoggerFactory.getLogger(GenreService.class);

        public void save(Genre genre) {
            logger.info("Creating genre: {}", genre.getName());
            genreRepo.save(genre);
        }

        public Optional<Genre> findById(Long id) {
            return genreRepo.findById(id);
        }

        public List<Genre> mapIdsToGenres(List<Long> ids){
        List<Genre> genres = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(genres::add));
        return genres;
    }
}
