package cinema.modal.response.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CinemaDTO {
    private String name;
    private List<String> image;
    private String location;
    private String status;
}
