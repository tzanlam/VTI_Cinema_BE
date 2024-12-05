package cinema.modal.entity;

import cinema.modal.entity.constant.StatusBooking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
@Table(name = "booking")
public class Booking extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "booking")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "more_service_id", referencedColumnName = "id")
    private MoreService moreServices;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    private Voucher vouchers;

    @Column(name = "total_price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusBooking status;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}