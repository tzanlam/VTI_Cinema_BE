package cinema.modal.entity;

import cinema.modal.entity.constant.StatusVoucher;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "voucher")
public class Voucher extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "expiry", nullable = false)
    private LocalDate expiry;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVoucher status;
}
