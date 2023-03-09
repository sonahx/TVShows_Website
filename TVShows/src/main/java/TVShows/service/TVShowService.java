package TVShows.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import TVShows.domain.TVShow;
import TVShows.repo.TVShowRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TVShowService {

    private final TVShowRepo showRepo;
    private final static Logger logger = LoggerFactory.getLogger(TVShowService.class);

    public void createShow(TVShow show) {
        logger.info("Creating show {}", show.getName());
        showRepo.save(show);
    }

    public TVShow findShowById(Long id) {
        logger.info("Looking for show with id: {}", id);
        return showRepo.getReferenceById(id);
    }

    public Optional<TVShow> findShowByName(String name) {
        logger.info("Looking for show with name: {}", name);
        return showRepo.findShowByName(name);
    }

    public List<TVShow> findAllShows() {
        logger.info("Looking for all the shows");
        return showRepo.findAll();
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
