package cinema.repository;

import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.StatusMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByStatus(StatusMovie status);
}
