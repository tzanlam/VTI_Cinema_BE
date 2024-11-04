package cinema.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "more_service")
public class MoreService extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private double price;
}
