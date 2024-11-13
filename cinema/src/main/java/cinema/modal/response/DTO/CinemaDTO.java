package cinema.modal.response.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CinemaDTO {
    private String cinema_id;
    private String cinema_name;
    private List<String> cinema_image;
    private String cinema_location;
    private String cinema_status;
}
