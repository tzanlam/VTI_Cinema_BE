package cinema.service.ShowTime;

import cinema.modal.entity.Movie;
import cinema.modal.entity.Room;
import cinema.modal.entity.ShowTime;
import cinema.modal.request.ShowTimeRequest;
import cinema.repository.MovieRepository;
import cinema.repository.RoomRepository;
import cinema.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static cinema.util.ConvertDateTime.convertToLocalDate;
import static cinema.util.ConvertDateTime.convertToLocalTime;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ShowTime findByID(int id) {
        return showTimeRepository.findById(id).get();
    }

    @Override
    public ShowTime createShowTime(ShowTimeRequest request) {
        ShowTime showTime =  new ShowTime();
        ShowTime s = populate(request, showTime);
        showTimeRepository.save(s);
        return s;
    }

    @Override
    public ShowTime updateShowTime(int id, ShowTimeRequest request) {
        ShowTime showTime = showTimeRepository.findById(id).orElse(null);
        if (showTime != null) {
            ShowTime s = populate(request, showTime);
            s.setId(id);
            showTimeRepository.save(s);
            return s;
        }
        return null;
    }

    @Override
    public List<LocalTime> findByMovie(int id) {
        ShowTime showTimes = showTimeRepository.findByMovieId(id);
        List<LocalTime> localTimeList = showTimes.getStartTime();
        return localTimeList;
    }

    private ShowTime populate(ShowTimeRequest request, ShowTime showTime) {
        Movie movie = movieRepository.findById(request.getMovie()).orElse(null);
        if (movie != null) {
            showTime.setMovie(movie);

            Room room = roomRepository.findById(request.getRoom()).orElse(null);
            if (room != null) {
                showTime.setRoom(room);
                showTime.setCinema(room.getCinema());
                showTime.setShowDate(convertToLocalDate(request.getShowDate()));
                int count = request.getStartTime().size();
                List<LocalTime> startTime = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    startTime.add(convertToLocalTime(request.getStartTime().get(i)));
                }
                showTime.setStartTime(startTime);
                return showTime;
            }
        }
        return null;
    }

}
