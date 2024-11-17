package cinema.modal.response.DTO;

import cinema.modal.entity.ShowTime;
import lombok.Data;

@Data
public class ShowTimeDTO {
    private String movie_name;
    private String cinema_name;
    private String show_date;
    private String start_time;

    public ShowTimeDTO(ShowTime showTime) {
        this.movie_name = String.valueOf(showTime.getId());
        this.cinema_name = String.valueOf(showTime.getCinema());
        this.show_date = String.valueOf(showTime.getShowDate());
        this.start_time = String.valueOf(showTime.getStartTime());
    }
}
