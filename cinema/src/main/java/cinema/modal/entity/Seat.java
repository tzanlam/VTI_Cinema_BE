package cinema.modal.entity;

import cinema.modal.entity.constant.SeatType;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType SeatType;

    @Column(name = "price", nullable = false)
    private double price;
}
