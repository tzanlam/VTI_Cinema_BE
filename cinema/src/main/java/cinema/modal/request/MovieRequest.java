package cinema.modal.request;

import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.Genre;
import cinema.modal.entity.constant.Language;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.entity.constant.ViewingAge;
import lombok.Data;

import static cinema.util.CheckEqualsEnum.checkEqualsEnum;
import static cinema.util.ConvertDateTime.convertToLocalDate;
import static cinema.util.ConvertDateTime.convertToLocalTime;

@Data

public class MovieRequest {
    private String image;
    private String name;
    private String actor;
    private String director;
    private String genre;
    private String duration;
    private String description;
    private String language;
    private String trailer;
    private String startDate;
    private String viewingAge;
    private String rating;

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
        movie.setImage(image);
        movie.setName(name);
        movie.setActor(actor);
        movie.setDirector(director);
        movie.setDuration(convertToLocalTime(duration));
        movie.setDescription(description);
        movie.setTrailer(trailer);
        movie.setRating(Float.parseFloat(rating));
        movie.setStartDate(convertToLocalDate(startDate));
        if (checkEqualsEnum(Genre.class, genre)) {
            movie.setGenre(Genre.valueOf(genre));
        }else {
            throw new IllegalArgumentException("Invalid genre");
        }
        if (checkEqualsEnum(Language.class, language)) {
            movie.setLanguage(Language.valueOf(language));
        }else {
            throw new IllegalArgumentException("Invalid language");
        }
        if (checkEqualsEnum(ViewingAge.class, viewingAge)) {
            movie.setViewingAge(ViewingAge.valueOf(viewingAge));
        }else {
            throw new IllegalArgumentException("Invalid viewing age");
        }
        movie.setStatus(StatusMovie.CLOSE);
    }
}
