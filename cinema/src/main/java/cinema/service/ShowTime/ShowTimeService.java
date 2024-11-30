package cinema.service.ShowTime;

import cinema.modal.entity.Seat;
import cinema.modal.entity.ShowTime;
import cinema.modal.request.ShowTimeRequest;

import java.time.LocalTime;
import java.util.List;

public interface ShowTimeService {
    ShowTime findByID(int id);
    ShowTime createShowTime(ShowTimeRequest request);
    ShowTime updateShowTime(int id, ShowTimeRequest request);
    List<LocalTime> findByMovie(int id, String date);
    List<Seat> findSeatRoomByMovieAndStartTime(int movieId, String startTime);
}
