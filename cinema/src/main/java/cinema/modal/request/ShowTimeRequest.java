package cinema.modal.request;

import cinema.modal.entity.ShowTime;
import lombok.Data;

@Data
public class ShowTimeRequest {
    private int movie;
    private int room;
    private int cinema;
    private String showDate;
    private String startTime;
}
