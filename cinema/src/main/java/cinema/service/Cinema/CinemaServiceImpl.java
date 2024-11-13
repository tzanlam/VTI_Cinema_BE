package cinema.service.Cinema;

import cinema.modal.entity.Cinema;
import cinema.modal.entity.constant.StatusCinema;
import cinema.modal.request.CinemaRequest;
import cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> findCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        return cinemas;
    }

    @Override
    public Cinema findById(int id) {
        return cinemaRepository.findById(id).get();
    }

    @Override
    public Cinema createCinema(CinemaRequest request) {
        Cinema cinema = request.asCinema();
        cinemaRepository.save(cinema);
        return cinema;
    }

    @Override
    public Cinema updateCinema(CinemaRequest request, int id) {
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        if (cinema.isPresent()) {
            Cinema c = cinema.get();
            request.updateCinema(c);
            c.setId(id);
            cinemaRepository.save(c);
            return c;
        }
        return null;
    }

    @Override
    public Cinema changeStatus(int id, String status) throws Exception {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new Exception("Cinema not found"));
        StatusCinema newStatus = StatusCinema.valueOf(status);
        List<StatusCinema> statusCinemas = List.of(StatusCinema.values());
        if (statusCinemas.contains(newStatus)) {
            cinema.setStatus(newStatus);
            cinemaRepository.save(cinema);
            return cinema;
        }
        return null;
    }
}