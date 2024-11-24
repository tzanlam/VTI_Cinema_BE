package cinema.service.ShowTime;

import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.ShowTime;
import cinema.modal.request.ShowTimeRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowTimeService {
    ShowTime findByID(int id);
    ShowTime createShowTime(ShowTimeRequest request);
    ShowTime updateShowTime(int id, ShowTimeRequest request);
    List<LocalTime> findByMovie(int id, String date);
    List<SeatRoom> findSeatRoomByMovieAndStartTime(int movieId, String startTime);
}
