package cinema.modal.response.DTO;

import cinema.modal.entity.Movie;
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
    private String trailer;
    private String release_date;
    private String viewing_age;
    private String rating;
    private String movie_status;

    public MovieDTO(Movie movie) {
        this.movie_id = String.valueOf(movie.getId());
        this.image = movie.getImage();
        this.movie_name = movie.getName();
        this.description = movie.getDescription();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.genre = String.valueOf(movie.getGenre());
        this.duration = String.valueOf(movie.getDuration());
        this.language = String.valueOf(movie.getLanguage());
        this.release_date = String.valueOf(movie.getStartDate());
        this.viewing_age = String.valueOf(movie.getViewingAge());
        this.trailer = String.valueOf(movie.getTrailer());
        this.rating = String.valueOf(movie.getRating());
        this.movie_status = String.valueOf(movie.getStatus());
    }
}
