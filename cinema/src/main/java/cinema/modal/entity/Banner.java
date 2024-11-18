package cinema.modal.entity;

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

    @Column(name = "is_Active")
    private boolean isActive;
}
