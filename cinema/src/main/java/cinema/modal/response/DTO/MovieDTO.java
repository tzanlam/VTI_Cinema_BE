package cinema.modal.response.DTO;

import lombok.Data;

@Data
public class MovieDTO {
    private String image;
    private String name;
    private String description;
    private String director;
    private String actor;
    private String genre;
    private String duration;
    private String language;
    private String startDate;
}
