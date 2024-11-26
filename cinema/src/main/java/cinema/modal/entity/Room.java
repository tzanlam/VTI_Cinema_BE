package cinema.modal.entity;

import cinema.modal.entity.constant.TypeScreen;
import cinema.modal.entity.constant.StatusRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "room")
public class Room extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<SeatRoom> seatRoom;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    @Column(name = "screen_type")
    @Enumerated(EnumType.STRING)
    private TypeScreen screenType;
}
