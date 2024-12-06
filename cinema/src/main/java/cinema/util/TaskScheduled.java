package cinema.util;

import cinema.modal.entity.Booking;
import cinema.modal.entity.Seat;
import cinema.modal.entity.Ticket;
import cinema.modal.entity.constant.StatusBooking;
import cinema.modal.entity.constant.StatusSeat;
import cinema.modal.entity.constant.StatusTicket;
import cinema.repository.BookingRepository;
import cinema.repository.SeatRepository;
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

    @Autowired
    private SeatRepository seatRepository;

    @Scheduled(fixedRate = 60000) // Chạy mỗi phút
    public void cancelExpiredBookings() {
        List<Booking> expiredBookings = bookingRepository.findByExpiryTimeBeforeAndStatus(
                LocalDateTime.now(), StatusBooking.PENDING
        );
        for (Booking booking : expiredBookings) {
            try {
                booking.setStatus(StatusBooking.CANCELLED);
                bookingRepository.save(booking);
                cancelTicketsAndUpdateSeats(booking);

            } catch (Exception e) {
                System.err.println("Error cancelling booking with ID: " + booking.getId() + " - " + e.getMessage());
            }
        }
    }

    private void cancelTicketsAndUpdateSeats(Booking booking) {
        for (Ticket ticket : booking.getTickets()) {
            ticket.setStatus(StatusTicket.CANCELLED);
            ticketRepository.save(ticket);
            Seat seat = ticket.getSeat();
            if (seat != null) {
                seat.setStatus(StatusSeat.AVAILABLE);
                seatRepository.save(seat);
            }
        }
    }
}
