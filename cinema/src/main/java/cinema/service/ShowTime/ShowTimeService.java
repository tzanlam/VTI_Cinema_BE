package cinema.service.ShowTime;

import cinema.modal.entity.ShowTime;
import cinema.modal.request.ShowTimeRequest;
import org.springframework.data.domain.Page;

public interface ShowTimeService {
    Page<ShowTime> findShowTimes(int page);
    ShowTime findByID(int id);
    ShowTime createShowTime(ShowTimeRequest request);
    ShowTime updateShowTime(int id, ShowTimeRequest request);

}
