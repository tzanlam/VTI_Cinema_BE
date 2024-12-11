package cinema.modal.entity;

import cinema.modal.entity.constant.City;
import cinema.modal.entity.constant.ContactStatus;
import cinema.modal.entity.constant.ServiceContact;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "contact")
@EqualsAndHashCode(callSuper = true)
public class Contact extends Base{
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

    @Enumerated(EnumType.STRING)
    @Column(name = "city", nullable = false)
    private City city;

    @Column(name = "service_contact")
    @Enumerated(EnumType.STRING)
    private ServiceContact serviceContact;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContactStatus contactStatus;

    @Column(name = "details", nullable = false)
    private String details;
}
