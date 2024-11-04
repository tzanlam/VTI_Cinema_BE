package cinema.modal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "seat_room_id", referencedColumnName = "id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "show_time_id", referencedColumnName = "id", nullable = false)
    private ShowTime showTime;

    @Column(name = "seat_quantity", nullable = false)
    private int seatQuantity;
}
