package cinema.service.Cinema;

import cinema.modal.entity.Cinema;
import cinema.modal.entity.Movie;
import cinema.modal.request.CinemaRequest;

import java.util.List;

public interface CinemaService {
    List<Cinema> findCinemas();
    Cinema findById(int id);
    Cinema createCinema(CinemaRequest request);
    Cinema updateCinema(CinemaRequest request, int id);
    Cinema changeStatus(int id, String status) throws Exception;
    List<Movie> findMoviesByCinema(int cinemaId);
}
