package com.TVShows.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.TVShows.domain.UsersShows;
import com.TVShows.repo.UsersShowsRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersShowsService {

	private final UsersShowsRepo repo;
	private final static Logger logger = LoggerFactory.getLogger(UsersShowsService.class);
	
	public void save(UsersShows usersShows) {
		logger.info("User {} added to his list - {} - {}",
				usersShows.getUser().getName(),
				usersShows.getTvShow().getName(),
				usersShows.getStatus());
		repo.save(usersShows);
	}
	public List<UsersShows> findAll() {
		return repo.findAll();
	}
}
