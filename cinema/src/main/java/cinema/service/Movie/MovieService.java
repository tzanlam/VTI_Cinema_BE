package cinema.service.Movie;

import cinema.modal.entity.Movie;
import cinema.modal.request.MovieRequest;

import java.util.List;

public interface MovieService {
    List<Movie> findMovies();
    Movie findById(int id);
    Movie createMovie(MovieRequest request);
    Movie updateMovie(int id, MovieRequest request);
    Movie changeStatus(int id, String status);
    List<Movie> findMovieComingSoon();
}
