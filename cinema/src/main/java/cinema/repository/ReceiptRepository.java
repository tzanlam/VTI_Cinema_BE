package cinema.repository;

import cinema.modal.entity.Receipt;
import cinema.modal.entity.constant.TypeReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findByType(TypeReceipt type);

    @Query("SELECT COUNT(r) > 0 FROM Receipt r WHERE r.booking.id = :bookingId")
    boolean existsByBookingId(@Param("bookingId") int bookingId);
}
