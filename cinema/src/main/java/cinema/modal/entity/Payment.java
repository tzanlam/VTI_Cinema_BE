package cinema.modal.entity;

import cinema.modal.entity.constant.TypePayment;
import cinema.modal.entity.constant.StatusPayment;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Payment")
public class Payment extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePayment method;

    @Column(name = "address_tranfer", nullable = false)
    private String addressTransfer;

    @Column(name = "qr_code", nullable = false)
    private String qrCode;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPayment status;
}
