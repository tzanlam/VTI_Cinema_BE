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
import java.util.Optional;

import static cinema.util.ConvertDateTime.convertToLocalDate;
import static cinema.util.ConvertDateTime.convertToLocalTime;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {

    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ShowTime findByID(int id) {
        return showTimeRepository.findById(id).orElse(null);
    }

    @Override
    public ShowTime createShowTime(ShowTimeRequest request) {
        ShowTime showTime = new ShowTime();
        ShowTime populatedShowTime = populate(request, showTime);
        if (populatedShowTime != null) {
            showTimeRepository.save(populatedShowTime);
            return populatedShowTime;
        }
        return null;
    }

    @Override
    public ShowTime updateShowTime(int id, ShowTimeRequest request) {
        Optional<ShowTime> optionalShowTime = showTimeRepository.findById(id);
        if (optionalShowTime.isPresent()) {
            ShowTime populatedShowTime = populate(request, optionalShowTime.get());
            if (populatedShowTime != null) {
                showTimeRepository.save(populatedShowTime);
                return populatedShowTime;
            }
        }
        return null;
    }

    @Override
    public List<LocalTime> findByMovie(int id) {
        ShowTime showTime = showTimeRepository.findByMovieId(id);
        return showTime != null ? showTime.getStartTime() : new ArrayList<>();
    }

    private ShowTime populate(ShowTimeRequest request, ShowTime showTime) {
        Optional<Movie> optionalMovie = movieRepository.findById(request.getMovie());
        if (optionalMovie.isPresent()) {
            showTime.setMovie(optionalMovie.get());

            Optional<Room> optionalRoom = roomRepository.findById(request.getRoom());
            if (optionalRoom.isPresent()) {
                Room room = optionalRoom.get();
                showTime.setRoom(room);
                showTime.setCinema(room.getCinema());
                showTime.setShowDate(convertToLocalDate(request.getShowDate()));

                List<LocalTime> startTimeList = new ArrayList<>();
                for (String time : request.getStartTime()) {
                    LocalTime convertedTime = convertToLocalTime(time);
                    if (convertedTime != null) {
                        startTimeList.add(convertedTime);
                    }
                }
                showTime.setStartTime(startTimeList);

                return showTime;
            }
        }
        return null;
    }
}
