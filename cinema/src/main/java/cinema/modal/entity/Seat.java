package cinema.modal.entity;

import cinema.modal.entity.constant.StatusSeat;
import cinema.modal.entity.constant.TypeSeat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "seat")
@EqualsAndHashCode(callSuper = true)
public class Seat extends Base{
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
    private TypeSeat type;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusSeat status;
}
