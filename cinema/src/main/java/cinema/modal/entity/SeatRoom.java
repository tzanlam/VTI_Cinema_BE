package cinema.modal.entity;

import cinema.modal.entity.constant.StatusSeatRoom;
import cinema.modal.entity.constant.TypeSeat;
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

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ElementCollection
    @CollectionTable(name = "row_seat", joinColumns = @JoinColumn(name = "seat_room_id"))
    @Column(name = "row_name")
    private List<String> rowNames;

    @Column(name = "type_seat")
    @Enumerated(EnumType.STRING)
    private TypeSeat typeSeat;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusSeatRoom Status;
}

