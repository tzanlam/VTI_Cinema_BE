package cinema.modal.entity;

import cinema.modal.entity.constant.StatusRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "seat_room")
public class SeatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "row", nullable = false)
    private int rowQuantity;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "seat_room_seat",
            joinColumns = @JoinColumn(name = "seat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;
}
