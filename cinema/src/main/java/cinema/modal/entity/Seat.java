package cinema.modal.entity;

import cinema.modal.entity.constant.StatusSeat;
import cinema.modal.entity.constant.TypeSeat;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private TypeSeat seatType;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusSeat status;
}
