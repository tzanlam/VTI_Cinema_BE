package cinema.modal.response.DTO;

import lombok.Data;

@Data
public class ShowTimeDTO {
    private String movieName;
    private String cinemaName;
    private String showDate;
    private String startTime;
}
