package cinema.modal.response.DTO;

import cinema.modal.entity.Cinema;
import lombok.Data;

import java.util.List;

@Data
public class CinemaDTO {
    private String cinema_id;
    private String cinema_name;
    private List<String> cinema_image;
    private String cinema_location;
    private String cinema_status;

    public CinemaDTO(Cinema cinema) {
        this.cinema_id = String.valueOf(cinema.getId());
        this.cinema_name = cinema.getName();
        this.cinema_image = cinema.getImage();
        this.cinema_location = cinema.getLocation();
        this.cinema_status = String.valueOf(cinema.getStatus());
    }
}
