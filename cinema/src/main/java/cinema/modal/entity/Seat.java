package cinema.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "price", nullable = false, unique = true)
    private double price;
}
