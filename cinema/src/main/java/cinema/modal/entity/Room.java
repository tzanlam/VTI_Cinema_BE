package cinema.modal.entity;

import cinema.modal.entity.constant.TypeScreen;
import cinema.modal.entity.constant.StatusRoom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "room")
public class Room extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
    private Cinema cinema;

    @Column(name = "screen_type")
    @Enumerated(EnumType.STRING)
    private TypeScreen screenType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    @OneToMany(mappedBy = "room")
    private List<Seat> seats;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowTime> showTimes;
}