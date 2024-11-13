package cinema.modal.entity;

import cinema.modal.entity.constant.StatusService;
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

    @Column(name = "decription")
    private String decription;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusService status;
}
