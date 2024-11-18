package cinema.service.Booking;

import cinema.modal.entity.*;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.request.BookingRequest;
import cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MoreServiceRepository moreServiceRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Booking> findBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        populate(booking, request);
        booking.setStatus(StatusBooking.WAITING);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking updateBooking(int id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            populate(booking, request);
            booking.setId(id);
            booking.setStatus(StatusBooking.WAITING);
            bookingRepository.save(booking);
            return booking;
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

    private Booking populate(Booking booking, BookingRequest request) {
        Optional<Account> account = accountRepository.findById(request.getAccount());
        Optional<Ticket> ticket = ticketRepository.findById(request.getTicket());
        Optional<MoreService> moreService = moreServiceRepository.findById(request.getMoreService());
        Optional<Voucher> voucher = voucherRepository.findById(request.getVoucher());

        if (account.isEmpty() || ticket.isEmpty() || moreService.isEmpty() || voucher.isEmpty()) {
            throw new IllegalArgumentException("Invalid account, ticket, more service, or voucher details");
        }
        booking.setAccount(account.get());
        booking.setTicket(ticket.get());
        booking.setMoreService(moreService.get());
        booking.setVoucher(voucher.get());
        booking.setTotalPrice(ticket.get().getTotalPrice() + moreService.get().getPrice() - voucher.get().getDiscount());
        return booking;
    }
}
