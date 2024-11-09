package cinema.modal.entity;

import cinema.modal.entity.constant.ScreenType;
import cinema.modal.entity.constant.StatusRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

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


//    @ElementCollection
//    @CollectionTable(name = "seatinroom", joinColumns = @JoinColumn(name = "seat_room_id"))
//    @Column (name = "seat_room_id", nullable = false)
//    private List<SeatRoom> seatRooms;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    @Column(name = "screen_type")
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;
}
