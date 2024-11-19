package cinema.modal.entity;

import cinema.modal.entity.constant.StatusBooking;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "booking")
public class Booking extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @OneToMany
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
    private List<Ticket> ticket;

    @ManyToOne
    @JoinColumn(name = "more_service_id", referencedColumnName = "id")
    private MoreService moreService;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    private Voucher voucher;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusBooking status;
}
