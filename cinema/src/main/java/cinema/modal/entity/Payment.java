package cinema.modal.entity;

import cinema.modal.entity.constant.MethodPayment;
import cinema.modal.entity.constant.StatusPayment;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private MethodPayment method;

    @Column(name = "address_tranfer", nullable = false)
    private int addressTransfer;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPayment status;
}
