package cinema.service.Movie;

import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.request.MovieRequest;
import cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public List<Movie> findMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie createMovie(MovieRequest request) {
        Movie movie = request.asMovie();
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(int id, MovieRequest request) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            Movie a = request.updateMovie(movie);
            movieRepository.save(a);
            return a;
        }
        return null;
    }

    @Override
    public Movie changeStatus(int id, String newStatus) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            List<StatusMovie> validStatuses = Arrays.asList(StatusMovie.values());
            StatusMovie statusMovie = StatusMovie.valueOf(newStatus);
            if (validStatuses.contains(statusMovie)) {
                movie.setStatus(StatusMovie.valueOf(newStatus));
                movieRepository.save(movie);
                return movie;
            } else {
                throw new IllegalArgumentException("status not support");
            }
        } else {
            System.out.println("Movie not found with id: " + id);
        }
        return null;
    }

    @Override
    public List<Movie> findMovieComingSoon() {
        return movieRepository.findByStatus(StatusMovie.COMING_SOON);
    }

    @Override
    public List<Movie> findMovieShowing() {
        return movieRepository.findByStatus(StatusMovie.SHOWING);
    }

    @Override
    public List<Movie> findMovieSpecial() {
        return movieRepository.findByStatus(StatusMovie.SPECIAL);
    }
}