package cinema.modal.response.DTO;

import lombok.Data;

@Data
public class MovieDTO {
    private String movie_id;
    private String image;
    private String movie_name;
    private String description;
    private String director;
    private String actor;
    private String genre;
    private String duration;
    private String language;
    private String release_date;
    private String viewing_age;
    private String rating;
    private String movie_status;
}
