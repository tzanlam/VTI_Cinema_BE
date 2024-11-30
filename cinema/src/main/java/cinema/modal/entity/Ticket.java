package cinema.modal.entity;

import cinema.modal.entity.constant.StatusTicket;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "show_time_id", referencedColumnName = "id", nullable = false)
    private ShowTime showTime;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTicket status;
}
