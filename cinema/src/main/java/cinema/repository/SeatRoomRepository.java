package cinema.repository;

import cinema.modal.entity.SeatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRoomRepository extends JpaRepository<SeatRoom, Integer> {
}
