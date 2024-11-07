package cinema.modal.entity;

import cinema.modal.entity.constant.ScreenType;
import cinema.modal.entity.constant.StatusSeatroom;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seat_room")
public class SeatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    @Column(name = "status_seat_room")
    @Enumerated(EnumType.STRING)
    private StatusSeatroom statusSeatroom;
}
