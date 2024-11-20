package cinema.modal.entity;

import cinema.modal.entity.constant.Role;
import cinema.modal.entity.constant.StatusAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "account")
public class Account extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",unique = true ,nullable = false)
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    @Column(name = "verification_code")
    private String verificationCode;

    public Account(String username, String fullname, LocalDate birthDate, String email, String password, String phoneNumber,Role role, StatusAccount status) {
        this.username = username;
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
}
