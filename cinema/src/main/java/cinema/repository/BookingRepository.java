package cinema.repository;

import cinema.modal.entity.Booking;
import cinema.modal.entity.constant.StatusBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByIdAndStatus(int id, StatusBooking status);
    Booking findByAccountIdAndStatus(int accountId, StatusBooking status);

    List<Booking> findByStatus(StatusBooking statusBooking);
}
