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
        Account account = accountRepository.findById(request.getAccount()).get();
        Voucher voucher = voucherRepository.findById(request.getVoucher()).get();
        Ticket ticket = ticketRepository.findById(request.getTicket()).get();
        MoreService moreService = moreServiceRepository.findById(request.getMoreService()).get();
        Payment payment = paymentRepository.findById(request.getPayment()).get();
        booking.setAccount(account);
        booking.setVoucher(voucher);
        booking.setTicket(ticket);
        booking.setMoreService(moreService);
        booking.setPayment(payment);
        booking.setConfirmed(false);
        booking.setTotalPrice(ticket.getTotalPrice()+moreService.getPrice()-voucher.getDiscount());
        booking.setStatus(StatusBooking.WAITING);
        bookingRepository.save(booking);
        return booking;
    }

//    @Scheduled(fixedRate = 60000)
//    public Booking removeExpiredBookings(BookingRequest request){
//        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
//        List<Booking> expiredBookings = bookingRepository.findByConfirmedFalseAndCreatedAtBefore(tenMinutesAgo);
//        bookingRepository.deleteAll(expiredBookings);
//        return null;
//    }

//    public Booking confirmBooking(int id) {
//       Booking
//    }

    @Override
    public Booking updateBooking(int id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            Account account = accountRepository.findById(request.getAccount()).get();
            Voucher voucher = voucherRepository.findById(request.getVoucher()).get();
            Ticket ticket = ticketRepository.findById(request.getTicket()).get();
            MoreService moreService = moreServiceRepository.findById(request.getMoreService()).get();
            Payment payment = paymentRepository.findById(request.getPayment()).get();
            booking.setAccount(account);
            booking.setVoucher(voucher);
            booking.setTicket(ticket);
            booking.setMoreService(moreService);
            booking.setPayment(payment);
            booking.setTotalPrice(ticket.getTotalPrice()+moreService.getPrice()-voucher.getDiscount());
            booking.setStatus(StatusBooking.WAITING);
            bookingRepository.save(booking);
            return booking;
        }
        return null;
    }

    @Override
    public Booking changeStatus(int id, String Newstatus) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        List<StatusBooking> statusBookings = Arrays.asList(StatusBooking.values());
        if (statusBookings.contains(StatusBooking.valueOf(Newstatus))) {
            booking.setStatus(StatusBooking.valueOf(Newstatus));
            bookingRepository.save(booking);
            return booking;
        }
        return null;
    }


}
