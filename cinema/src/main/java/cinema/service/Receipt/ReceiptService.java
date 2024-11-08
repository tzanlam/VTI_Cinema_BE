package cinema.service.Receipt;

import cinema.modal.entity.Booking;
import cinema.modal.entity.Receipt;
import cinema.modal.request.ReceiptRequest;
import org.springframework.data.domain.Page;

public interface ReceiptService {
    Page<Receipt> findReceipts(int page);
    Receipt findReceiptById(int id);
    Receipt ceateReceipt(ReceiptRequest request);
    Receipt updateReceipt(int id, ReceiptRequest request);
    Receipt changeStatus(int id, String status);
    Receipt isBooking(Booking booking);
    Page<Receipt> findIncome(int page);
    Page<Receipt> findSpending(int page);
}
