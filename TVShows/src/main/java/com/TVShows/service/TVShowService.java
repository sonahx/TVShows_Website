package com.TVShows.service;

import com.TVShows.domain.TVShow;
import com.TVShows.repo.TVShowRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TVShowService {

    private final TVShowRepo showRepo;
    private final static Logger logger = LoggerFactory.getLogger(TVShowService.class);

    public void createShow(TVShow show) {
        logger.info("Creating show {}", show.getName());
        showRepo.save(show);
    }

    public Optional<TVShow> findShowById(Long id) {
        logger.info("Looking for show with id: {}", id);
        return showRepo.findById(id);
    }

    public Optional<TVShow> findShowByName(String name) {
        logger.info("Looking for show with name: {}", name);
        return showRepo.findShowByName(name);
    }

    public List<TVShow> findAllShows() {
        logger.info("Looking for all the shows");
        return showRepo.findAll();
    }

    public List<TVShow> findShowsByKeyword(String keyword){
        logger.info("Looking for show by keyword '{}'", keyword);
        return showRepo.findShowsByKeyword(keyword);
    }

    public Page<TVShow> findAllShowsWithPagination(int page, int size) {
        logger.info("Looking for all the shows with pagination");
        return showRepo.findAll(PageRequest.of(page,size));
    }

    public void updateShow(TVShow show) {
        logger.info("Updating show: {}, id:{}", show.getName(), show.getId());
        showRepo.save(show);
    }

    public void removeShow(TVShow show) {
        logger.info("Removing show: {}, id:{}", show.getName(), show.getId());
        showRepo.delete(show);
    }
}
