package cinema.modal.response.DTO;

import lombok.Data;

@Data
public class ShowTimeDTO {
    private String movie_name;
    private String cinema_name;
    private String show_date;
    private String start_time;
}
