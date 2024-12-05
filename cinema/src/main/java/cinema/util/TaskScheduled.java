package cinema.util;

import cinema.modal.entity.Booking;
import cinema.modal.entity.Ticket;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.entity.constant.StatusTicket;
import cinema.repository.BookingRepository;
import cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskScheduled {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Scheduled(fixedRate = 60000) // Chạy mỗi phút
    public void cancelExpiredBookings() {
        List<Booking> expiredBookings = bookingRepository.findByExpiryTimeBeforeAndStatus(
                LocalDateTime.now(), StatusBooking.PENDING
        );
        for (Booking booking : expiredBookings) {
            booking.setStatus(StatusBooking.CANCELLED);
            bookingRepository.save(booking);

            // Hủy các vé liên quan
            for (Ticket ticket : booking.getTickets()) {
                ticket.setStatus(StatusTicket.CANCELLED);
                ticketRepository.save(ticket);
            }
        }
    }

}
