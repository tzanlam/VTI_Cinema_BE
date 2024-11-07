package cinema.modal.request;

import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.Genre;
import cinema.modal.entity.constant.Language;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.entity.constant.ViewingAge;
import lombok.Data;

import static cinema.util.ConvertDateTime.convertToLocalTime;

@Data

public class MovieRequest {
    private String name;
    private String actor;
    private String director;
    private String genre;
    private String duration;
    private String description;
    private String language;
    private String trailer;
    private String viewingAge;
    private float rating;
    private String status;

    public Movie asMovie() {
        Movie movie = new Movie();
        populateMovieFields(movie);
        return movie;
    }

    public Movie updateMovie(Movie movie) {
        populateMovieFields(movie);
        return movie;
    }

    private void populateMovieFields(Movie movie) {
        movie.setName(name);
        movie.setActor(actor);
        movie.setDirector(director);
        movie.setDuration(convertToLocalTime(duration));
        movie.setDescription(description);
        movie.setTrailer(trailer);
        movie.setRating(rating);

        movie.setGenre(convertEnum(Genre.class, genre));
        movie.setLanguage(convertEnum(Language.class, language));
        movie.setViewingAge(convertEnum(ViewingAge.class, viewingAge));
        movie.setStatus(convertEnum(StatusMovie.class, status));
    }
    private <E extends Enum<E>> E convertEnum(Class<E> enumClass, String value) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
