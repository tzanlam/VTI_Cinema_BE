package cinema.modal.entity;

import cinema.modal.entity.constant.StatusCinema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    // Thêm quan hệ với Movie qua ShowTime
    @OneToMany(mappedBy = "cinema")
    @JsonIgnore
    private List<ShowTime> showTimes;

    public List<Movie> getMovies() {
        // Lấy danh sách phim từ các ShowTime
        return showTimes.stream()
                .map(ShowTime::getMovie)
                .distinct()  // Đảm bảo mỗi phim chỉ xuất hiện 1 lần
                .collect(Collectors.toList());
    }
}
