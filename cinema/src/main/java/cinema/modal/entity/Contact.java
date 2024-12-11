package cinema.modal.entity;

import cinema.modal.entity.constant.ContactStatus;
import cinema.modal.entity.constant.ServiceContact;
import cinema.modal.entity.constant.StatusRoom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "fullname", unique = true, nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
    private Cinema cinema;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "service_contact")
    @Enumerated(EnumType.STRING)
    private ServiceContact serviceContact;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContactStatus contactStatus;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "contact_date", unique = true, nullable = false)
    private LocalDate date;
}
