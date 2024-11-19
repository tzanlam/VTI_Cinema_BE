package cinema.repository;

import cinema.modal.entity.Receipt;
import cinema.modal.entity.constant.TypeReceipt;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findByType(TypeReceipt type);
}
