package cinema.modal.entity;

import cinema.modal.entity.constant.StatusCinema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cinema")
public class Cinema extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "cinema_images", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "image_url")
    private List<String> image;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCinema status;
}
