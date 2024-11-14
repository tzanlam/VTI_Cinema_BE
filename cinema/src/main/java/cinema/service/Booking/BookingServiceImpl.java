package cinema.service.Booking;

import cinema.modal.entity.*;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.request.BookingRequest;
import cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private MoreServiceRepository moreServiceRepository;

    @Override
    public Page<Booking> findBookings(int page) {
        return bookingRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        if (populate(booking, request)) {
            booking.setConfirmed(false);
            booking.setStatus(StatusBooking.WAITING);
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new IllegalArgumentException("Invalid booking request details");
        }
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredBookings() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
            List<Booking> expiredBookings = bookingRepository.findByConfirmedFalseAndCreatedDateBefore(tenMinutesAgo);
        bookingRepository.deleteAll(expiredBookings);
    }

    public Booking confirmBooking(int id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null && !booking.isConfirmed()) {
            booking.setConfirmed(true);
            booking.setStatus(StatusBooking.SUCCESS);
            bookingRepository.save(booking);
            return booking;
        } else {
            throw new IllegalArgumentException("Booking not found or already confirmed");
        }
    }

    @Override
    public Booking updateBooking(int id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            if (populate(booking, request)) {
                booking.setId(id);
                booking.setStatus(StatusBooking.WAITING);
                booking.setConfirmed(false);
                bookingRepository.save(booking);
                return booking;
            } else {
                throw new IllegalArgumentException("Invalid booking request details");
            }
        }
        throw new IllegalArgumentException("Booking not found");
    }

    @Override
    public Booking changeStatus(int id, String newStatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            try {
                StatusBooking status = StatusBooking.valueOf(newStatus);
                booking.setStatus(status);
                bookingRepository.save(booking);
                return booking;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value");
            }
        } else {
            throw new IllegalArgumentException("Booking not found");
        }
    }

    private boolean populate(Booking booking, BookingRequest request) {
        Optional<Account> account = accountRepository.findById(request.getAccount());
        Optional<Ticket> ticket = ticketRepository.findById(request.getTicket());
        Optional<MoreService> moreService = moreServiceRepository.findById(request.getMoreService());
        Optional<Payment> payment = paymentRepository.findById(request.getPayment());
        Optional<Voucher> voucher = voucherRepository.findById(request.getVoucher());

        if (account.isPresent() && ticket.isPresent() && moreService.isPresent() && payment.isPresent() && voucher.isPresent()) {
            booking.setAccount(account.get());
            booking.setTicket(ticket.get());
            booking.setMoreService(moreService.get());
            booking.setPayment(payment.get());
            booking.setVoucher(voucher.get());
            booking.setTotalPrice(ticket.get().getTotalPrice() + moreService.get().getPrice() - voucher.get().getDiscount());
            return true;
        }
        return false;
    }
}
