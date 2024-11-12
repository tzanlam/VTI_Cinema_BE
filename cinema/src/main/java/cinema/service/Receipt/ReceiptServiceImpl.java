package cinema.service.Receipt;

import cinema.modal.entity.Account;
import cinema.modal.entity.Booking;
import cinema.modal.entity.Receipt;
import cinema.modal.entity.constant.StatusReceipt;
import cinema.modal.entity.constant.TypeReceipt;
import cinema.modal.request.ReceiptRequest;
import cinema.repository.AccountRepository;
import cinema.repository.BookingRepository;
import cinema.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Page<Receipt> findReceipts(int page) {
        return receiptRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Receipt findReceiptById(int id) {
        return receiptRepository.findById(id).orElse(null);
    }

    @Override
    public Receipt ceateReceipt(ReceiptRequest request) {
        Receipt receipt = new Receipt();
        Account account = accountRepository.findById(request.getAccount()).get();
        receipt.setAccount(account);
        List<TypeReceipt> typeReceipts = List.of(TypeReceipt.values());
        if (typeReceipts.contains(request.getTypeReceipt())) {
            receipt.setType(TypeReceipt.valueOf(request.getTypeReceipt()));
        }
        receipt.setReason(request.getReason());
        receipt.setAmount(Double.parseDouble(request.getAmount()));
        receipt.setStatus(StatusReceipt.UNPROCESSED);
        return receipt;
    }

    @Override
    public Receipt updateReceipt(int id, ReceiptRequest request) {
        Receipt receipt = receiptRepository.findById(id).orElse(null);
        if (receipt != null) {
            List<TypeReceipt> typeReceipts = List.of(TypeReceipt.values());
            if (typeReceipts.contains(request.getTypeReceipt())) {
                receipt.setType(TypeReceipt.valueOf(request.getTypeReceipt()));
            }
            Account account = accountRepository.findById(request.getAccount()).get();
            receipt.setAccount(account);
            receipt.setReason(request.getReason());
            receipt.setAmount(Double.parseDouble(request.getAmount()));
            receipt.setStatus(StatusReceipt.UNPROCESSED);
            receiptRepository.save(receipt);
            return receipt;
        }
        return null;
    }

    @Override
    public Receipt changeStatus(int id, String status) {
        Receipt receipt = receiptRepository.findById(id).orElse(null);
        if (receipt != null) {
            List<StatusReceipt> statusReceipts = List.of(StatusReceipt.valueOf(status));
            if (statusReceipts.contains(status)) {
                receipt.setStatus(StatusReceipt.valueOf(status));
            }
            receiptRepository.save(receipt);
            return receipt;
        }
        return null;
    }

    @Override
    public Page<Receipt> findIncome(int page) {
        return receiptRepository.findByType(TypeReceipt.INCOME, PageRequest.of(page, 10));
    }

    @Override
    public Page<Receipt> findSpending(int page) {
        return receiptRepository.findByType(TypeReceipt.SPENDING, PageRequest.of(page, 10));
    }

    @Override
    public Receipt isBooking(Booking booking) {
        Receipt receipt = new Receipt();
        receipt.setType(TypeReceipt.INCOME);
        receipt.setAccount(booking.getAccount());
        receipt.setBooking(booking);
        receipt.setReason("THU NHAP DAT VE");
        receipt.setAmount(booking.getTotalPrice());
        receipt.setStatus(StatusReceipt.PROCESSED);
        receiptRepository.save(receipt);
        return receipt;
    }
}
