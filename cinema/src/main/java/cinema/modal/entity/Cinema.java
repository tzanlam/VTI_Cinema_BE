package cinema.modal.entity;

import cinema.modal.entity.constant.StatusCinema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cinema")
public class Cinema extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // tao table phu de luu tru anh
    @ElementCollection
    @CollectionTable(name = "image_list", joinColumns = @JoinColumn(name = "imgMain_id"))
    @Column(name = "image")
    private List<String> image;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCinema status;
}
