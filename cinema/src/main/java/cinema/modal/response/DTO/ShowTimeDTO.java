package cinema.modal.response.DTO;

import cinema.modal.entity.ShowTime;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShowTimeDTO {
    private String showTime_id;
    private String movie_name;
    private String cinema_name;
    private String show_date;
    private List<String> start_time;

    public ShowTimeDTO(ShowTime showTime) {
        this.showTime_id = String.valueOf(showTime.getId());
        this.movie_name = showTime.getMovie() != null ? showTime.getMovie().getName() : "N/A";
        this.cinema_name = showTime.getCinema() != null ? showTime.getCinema().getName() : "N/A";
        this.show_date = showTime.getShowDate() != null ? showTime.getShowDate().toString() : "N/A";

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        this.start_time = showTime.getStartTime() != null
                ? showTime.getStartTime().stream()
                .map(localTime -> localTime.format(timeFormatter))
                .collect(Collectors.toList())
                : List.of("N/A");
    }
}
