package cinema.repository;

import cinema.modal.entity.SeatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRoomRepository extends JpaRepository<SeatRoom, Integer> {
    SeatRoom findByName(String name);
}
