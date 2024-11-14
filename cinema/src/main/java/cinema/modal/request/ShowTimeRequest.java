package cinema.modal.request;

import lombok.Data;

import java.util.List;

@Data
public class ShowTimeRequest {
    private int movie;
    private int room;
    private String showDate;
    private List<String> startTime;
}