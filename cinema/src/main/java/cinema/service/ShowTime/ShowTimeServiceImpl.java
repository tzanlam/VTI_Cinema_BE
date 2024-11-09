package cinema.service.ShowTime;

import cinema.modal.entity.ShowTime;
import cinema.modal.request.ShowTimeRequest;
import cinema.repository.CinemaRepository;
import cinema.repository.MovieRepository;
import cinema.repository.RoomRepository;
import cinema.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static cinema.util.ConvertDateTime.convertToLocalDate;
import static cinema.util.ConvertDateTime.convertToLocalTime;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Page<ShowTime> findShowTimes(int page) {
        return showTimeRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public ShowTime findByID(int id) {
        return showTimeRepository.findById(id).get();
    }

    @Override
    public ShowTime createShowTime(ShowTimeRequest request) {
        ShowTime showTime = new ShowTime();
        showTime.setCinema(cinemaRepository.findById(request.getCinema()).get());
        showTime.setRoom(roomRepository.findById(request.getRoom()).get());
        showTime.setMovie(movieRepository.findById(request.getMovie()).get());
        showTime.setShowDate(convertToLocalDate(request.getShowDate()));
        showTime.setStartTime(convertToLocalTime(request.getStartTime()));
        showTimeRepository.save(showTime);
        return showTime;
    }

    @Override
    public ShowTime updateShowTime(int id, ShowTimeRequest request) {
        ShowTime showTime = showTimeRepository.findById(id).orElse(null);
        if (showTime != null) {
            showTime.setCinema(cinemaRepository.findById(request.getCinema()).get());
            showTime.setRoom(roomRepository.findById(request.getRoom()).get());
            showTime.setMovie(movieRepository.findById(request.getMovie()).get());
            showTime.setShowDate(convertToLocalDate(request.getShowDate()));
            showTime.setStartTime(convertToLocalTime(request.getStartTime()));
            showTimeRepository.save(showTime);
            return showTime;
        }
        return null;
    }
}
