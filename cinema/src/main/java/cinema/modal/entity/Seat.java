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

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private TypeSeat SeatType;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusSeat Status;
}
