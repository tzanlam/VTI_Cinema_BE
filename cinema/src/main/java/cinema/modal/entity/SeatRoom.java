package cinema.modal.entity;

import cinema.modal.entity.constant.StatusSeatroom;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "seat_room")
public class SeatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name" , nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room; // Liên kết với Room

    @ManyToMany
    @JoinTable(
            name = "seat_room_seat",
            joinColumns = @JoinColumn(name = "seat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;
    
    @Column(name = "status_seat_room")
    @Enumerated(EnumType.STRING)
    private StatusSeatroom statusSeatroom;
}
