package cinema.modal.request;

import lombok.Data;

import java.util.List;

@Data
public class ShowTimeRequest {
    private int movieId;
    private int roomId;
    private String showDate;
    private List<String> startTime;
}