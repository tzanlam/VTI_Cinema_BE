package cinema.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "showtime")
public class ShowTime extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
    private Cinema cinema;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @ElementCollection
    @CollectionTable(name = "list_start_time", joinColumns = @JoinColumn(name = "showtime_id"))
    @Column(name = "start_time", nullable = false)
    private List<LocalTime> startTime;

    @Column(name = "available_seats")
    private int availableSeats;
}

//dau ra
// id: 1, movie [id: 1,....], room[], cinema[], showDate: showdate, startTime: List
