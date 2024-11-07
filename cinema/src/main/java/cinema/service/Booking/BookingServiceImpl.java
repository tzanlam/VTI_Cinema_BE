package cinema.service.Booking;

import cinema.modal.entity.*;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.request.BookingRequest;
import cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        Account account = accountRepository.findById(request.getAccount()).get();
        Voucher voucher = voucherRepository.findById(request.getVoucher()).get();
        Ticket ticket = ticketRepository.findById(request.getTicket()).get();
        MoreService moreService = moreServiceRepository.findById(request.getMoreService()).get();
        Payment payment = paymentRepository.findById(request.getPayment()).get();
        Booking booking = new Booking();
        booking.setAccount(account);
        booking.setVoucher(voucher);
        booking.setTicket(ticket);
        booking.setMoreService(moreService);
        booking.setPayment(payment);
        //booking.setTotalPrice(ticket.get);
        booking.setStatus(StatusBooking.WAITING);
        bookingRepository.save(booking);
        return null;
    }

    @Override
    public Booking updateBooking(int id, BookingRequest request) {
        return null;
    }

    @Override
    public Booking changeStatus(int id) {
        return null;
    }
}
