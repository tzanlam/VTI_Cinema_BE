package cinema.repository;

import cinema.modal.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Modifying
    @Query("UPDATE Voucher v SET v.status = 'EXPIRED' WHERE v.expiry < :today AND v.status != 'EXPIRED'")
    void markExpiredVouchers(@Param("today") LocalDate today);
}
