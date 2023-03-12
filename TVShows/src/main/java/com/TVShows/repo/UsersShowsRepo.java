package com.TVShows.repo;

import com.TVShows.domain.UsersShows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersShowsRepo extends JpaRepository<UsersShows, Long>{

}
