package com.TVShows.service;

import com.TVShows.domain.TVShow;
import com.TVShows.domain.User;
import com.TVShows.domain.UsersShowProgress;
import com.TVShows.repo.UsersShowProgressRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersShowProgressService {

	private final UsersShowProgressRepo repo;
	private final static Logger logger = LoggerFactory.getLogger(UsersShowProgressService.class);
	
	public void save(UsersShowProgress progress) {
		logger.info("User {} added to his list - {} - {}",
				progress.getUser().getName(),
				progress.getTvShow().getName(),
				progress.getStatus());
		repo.save(progress);
	}

	public List<UsersShowProgress> findAll() {
		return repo.findAll();
	}

	public List<UsersShowProgress> findByUser(User user) {
		return repo.findByUser(user);
	}

	public Optional<UsersShowProgress> findByShowAndUser(TVShow tvShow, User user){
		return repo.findByTvShowAndUser(tvShow, user);
	}

	public void update(UsersShowProgress progress) {
		logger.info("User {} updated - {} - {} - {}",
				progress.getUser().getName(),
				progress.getTvShow().getName(),
				progress.getStatus(),
				(progress.getEpisodeProgress() + "/" + progress.getTvShow().getEpisodesNumber()));
		repo.save(progress);
	}
}
