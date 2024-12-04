package cinema.modal.response.DTO;

import cinema.modal.entity.ShowTime;
import lombok.Data;

@Data
public class ShowTimeDTO {
    private String show_time_id;
    private String movie;
    private String room;
    private String cinema;
    private String show_time_show_date;
    private String show_time_start_time;

    public ShowTimeDTO(ShowTime showTime) {
        this.show_time_id = String.valueOf(showTime.getId());
        this.movie = String.valueOf(showTime.getMovie().getName());
        this.room = String.valueOf(showTime.getRoom());
        this.cinema = String.valueOf(showTime.getRoom().getCinema());
        this.show_time_show_date = String.valueOf(showTime.getShowDate());
        this.show_time_start_time = String.valueOf(showTime.getStartTime());
    }
}
