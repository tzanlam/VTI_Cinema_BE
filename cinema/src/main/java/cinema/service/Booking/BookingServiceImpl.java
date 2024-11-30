package cinema.service.Booking;

import cinema.modal.entity.*;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.entity.constant.StatusTicket;
import cinema.modal.request.BookingRequest;
import cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

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
        booking.setStatus(StatusBooking.PENDING);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Booking updateBooking(int id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            populate(booking, request);
            booking.setId(id);
            booking.setStatus(StatusBooking.PENDING);
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

    private void populate(Booking booking, BookingRequest request) {
        Ticket ticket = ticketRepository.findByIdAndStatus(request.getTicketId(), StatusTicket.UNPAID);
        Account account = Objects.requireNonNull(ticket).getAccount();
        Optional<MoreService> moreService = moreServiceRepository.findById(request.getMoreServiceId());
        Optional<Voucher> voucher = voucherRepository.findById(request.getVoucherId());

        if (Objects.isNull(account) || moreService.isEmpty() || voucher.isEmpty()) {
            throw new IllegalArgumentException("Invalid account, ticket, more service, or voucher details");
        }
        booking.setAccount(account);
        booking.setTickets((List<Ticket>) ticket);
        booking.setMoreServices(moreService.get());
        booking.setVouchers(voucher.get());
        booking.setPrice(ticket.getPrice() + moreService.get().getPrice() - voucher.get().getDiscount());
    }
}
