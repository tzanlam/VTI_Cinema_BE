package cinema.service.Receipt;

import cinema.modal.entity.Account;
import cinema.modal.entity.Booking;
import cinema.modal.entity.Payment;
import cinema.modal.entity.Receipt;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.entity.constant.StatusReceipt;
import cinema.modal.entity.constant.TypeReceipt;
import cinema.modal.request.ReceiptRequest;
import cinema.modal.request.UserPaymentRequest;
import cinema.repository.AccountRepository;
import cinema.repository.BookingRepository;
import cinema.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static cinema.util.CheckEqualsEnum.checkEqualsEnum;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Receipt> findReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt findReceiptById(int id) {
        return receiptRepository.findById(id).orElse(null);
    }

    @Override
    public Receipt ceateReceipt(ReceiptRequest request) {
        Receipt receipt = new Receipt();
        populateReceipt(request, receipt);
        receiptRepository.save(receipt);
        return receipt;
    }

    @Override
    public Receipt updateReceipt(int id, ReceiptRequest request) {
        Receipt receipt = receiptRepository.findById(id).orElse(null);
        if (receipt != null) {
            populateReceipt(request, receipt);
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
            StatusReceipt statusReceipt = StatusReceipt.valueOf(status);
            if (statusReceipts.contains(statusReceipt)) {
                receipt.setStatus(StatusReceipt.valueOf(status));
            }
            receiptRepository.save(receipt);
            return receipt;
        }
        return null;
    }

    @Override
    public List<Receipt> findIncome() {
        return receiptRepository.findByType(TypeReceipt.INCOME);
    }

    @Override
    public List<Receipt> findSpending() {
        return receiptRepository.findByType(TypeReceipt.SPENDING);
    }

    @Scheduled(fixedRate = 5000) // Chạy mỗi 5 giây
    @Transactional
    @Override
    public String isBooking(Booking booking) {
        Receipt receipt = new Receipt();
        if (booking.getStatus().equals(StatusBooking.SUCCESS)) {
            receipt.setType(TypeReceipt.INCOME);
            receipt.setAccount(booking.getAccount());
            receipt.setReason("THU NHAP DAT VE");
            receipt.setAmount(booking.getTotalPrice());
            receipt.setStatus(StatusReceipt.PROCESSED);
            receiptRepository.save(receipt);
            return "You have new income receipt";
        }
        else
            return "You have no new income receipt";
    }

    private void populateReceipt(ReceiptRequest request, Receipt receipt){;
        boolean checkType = checkEqualsEnum(TypeReceipt.class, request.getTypeReceipt());
        if (checkType) {
            receipt.setType(TypeReceipt.valueOf(request.getTypeReceipt()));
        }
        Account account = accountRepository.findById(request.getAccount()).orElse(null);
        receipt.setAccount(account);
        receipt.setReason(request.getReason());
        receipt.setAmount(Objects.requireNonNull(bookingRepository.findById(request.getBooking()).orElse(null)).getTotalPrice());
        receipt.setStatus(StatusReceipt.PROCESSED);
    }


    @Scheduled(fixedRate = 1000*60*10)
    public void confirmPayment(int bookingId, UserPaymentRequest request){
        Booking booking = bookingRepository.findByAccountIdAndStatus(bookingId, StatusBooking.WAITING);
        if(Objects.nonNull(booking)){

        }
    }
}
