package cinema.service.Movie;

import cinema.modal.entity.Account;
import cinema.modal.entity.Cinema;
import cinema.modal.entity.Movie;
import cinema.modal.request.AccountRequest;
import cinema.modal.request.MovieRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    List<Movie> findMovies();
    Movie findById(int id);
    Movie createMovie(MovieRequest request);
    Movie updateMovie(int id, MovieRequest request);
    Movie changeStatus(int id, String status);
}
