package cinema.modal.entity;

import cinema.modal.entity.constant.StatusBanner;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "banner")
public class Banner extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusBanner status;
}