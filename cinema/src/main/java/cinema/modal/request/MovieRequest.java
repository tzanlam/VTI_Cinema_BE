package cinema.modal.request;

import cinema.modal.entity.Account;
import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.Genre;
import cinema.modal.entity.constant.Language;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.entity.constant.ViewingAge;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalTime;

import static cinema.util.ConvertDateTime.convertToLocalDate;
import static cinema.util.ConvertDateTime.convertToLocalTime;

@Data

public class MovieRequest {
    private String name;
    private String actor;
    private String director;
    private Genre genre;
    private String duration;
    private String description;
    private Language language;
    private String trailer;
    private ViewingAge viewingAge;
    private float rating;
    private StatusMovie status;

    public Movie asMovie() {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setActor(actor);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setDuration(convertToLocalTime(duration));
        movie.setDescription(description);
        movie.setLanguage(language);
        movie.setTrailer(trailer);
        movie.setViewingAge(viewingAge);
        movie.setRating(rating);

        return movie;
    }
    public  Movie updateMovie(Movie movie) {
        movie.setName(name);
        movie.setActor(actor);
        movie.setDirector(director);

        movie.setGenre(genre);
        movie.setDescription(description);
        movie.setLanguage(language);
        movie.setTrailer(trailer);
        movie.setViewingAge(viewingAge);
        movie.setRating(rating);
        return movie;
    }
}
