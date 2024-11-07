package cinema.modal.entity;

import cinema.modal.entity.constant.ScreenType;
import cinema.modal.entity.constant.StatusRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "room")
public class Room extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false, unique = true)
    private Cinema cinema;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SeatRoom> seatRooms;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    @Column(name = "screen_type")
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;
}
