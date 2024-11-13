package cinema.modal.entity;

import cinema.modal.entity.constant.Genre;
import cinema.modal.entity.constant.Language;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.entity.constant.ViewingAge;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "movie")
public class Movie extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_movie", nullable = false)
    private String image;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "actor")
    private String actor;

    @Column(name = "director")
    private String director;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "description")
    private String description;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "trailer", nullable = false)
    private String trailer;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "viewing_age", nullable = false)
    @Enumerated(EnumType.STRING)
    private ViewingAge viewingAge;

    @Column(name = "rating")
    private float rating;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusMovie status;
}
