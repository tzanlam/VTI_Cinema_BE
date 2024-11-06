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
public class MovieServiceimpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public List<Movie> findMovies() {
        return List.of((Movie) movieRepository.findAll());
    }

    @Override
    public Movie findById(int id) {
        return movieRepository.findById(id).get();
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
            List<StatusMovie> validStatuses = Arrays.asList(StatusMovie.COMING_SOON, StatusMovie.SHOWING, StatusMovie.CLOSED);

            if (validStatuses.contains(newStatus)) {
                movie.setStatus(StatusMovie.valueOf(newStatus));
                movieRepository.save(movie);
                return movie;
            } else {
                throw new IllegalArgumentException("Trạng thái không hợp lệ");
            }
        } else {
            System.out.println("Không tìm thấy bộ phim đang chiếu với ID: " + id);
        }
        return null;
    }

}
