package cinema.modal.entity;

import cinema.modal.entity.constant.SeatType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "seat_number", nullable = false, unique = true)
    private String seat_number;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType SeatType;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SeatRoom> seatRooms;

    @Column(name = "price", nullable = false)
    private double price;
}
