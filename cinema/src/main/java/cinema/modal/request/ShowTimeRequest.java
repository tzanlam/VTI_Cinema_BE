package cinema.modal.request;

import lombok.Data;

import java.util.List;

@Data
public class ShowTimeRequest {
    private int movie;
    private int room;
    private String showDate;
    private List<String> startTime;
    //2 gia tri ->  list<"12:00:00","13:00:00")
    //                      0           1
    // => List<LocalTime> startTime = (12h 13h)
}
