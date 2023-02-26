package TVShows.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TVShows.domain.UsersShows;

@Repository
public interface UsersShowsRepo extends JpaRepository<UsersShows, Long>{

}
