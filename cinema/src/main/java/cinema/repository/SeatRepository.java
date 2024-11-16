package cinema.repository;

import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.TypeSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findBySeatType(TypeSeat seatType);
}
