package cinema.modal.response.DTO;

import cinema.modal.entity.ShowTime;
import lombok.Data;

@Data
public class ShowTimeDTO {
    private String show_time_id;
    private String movie_name;
    private String cinema_name;
    private String show_date;
    private String start_time;

    public ShowTimeDTO(ShowTime showTime) {
        this.show_time_id = String.valueOf(showTime.getId());
        this.movie_name = String.valueOf(showTime.getMovie().getName());
        this.cinema_name = String.valueOf(showTime.getCinema().getName());
        this.show_date = String.valueOf(showTime.getShowDate());
        this.start_time = String.valueOf(showTime.getStartTime());
    }
}
