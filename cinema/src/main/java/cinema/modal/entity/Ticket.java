package cinema.modal.entity;

import cinema.modal.entity.constant.StatusTicket;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "seat_room_id", referencedColumnName = "id", nullable = false)
    private SeatRoom seat;

    @ElementCollection
    @CollectionTable(name = "seat_room_select", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "seat_select", nullable = false)
    private List<String> seatSelect;
    // fe chon ghe tra ve List<String> ten ghe (A1, A2).length => tao ticket
    @ManyToOne
    @JoinColumn(name = "show_time_id", referencedColumnName = "id", nullable = false)
    private ShowTime showTime;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTicket status;
}
