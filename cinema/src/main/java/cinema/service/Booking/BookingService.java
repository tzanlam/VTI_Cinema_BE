package cinema.service.Booking;

import cinema.modal.entity.Booking;
import cinema.modal.request.BookingRequest;
import org.springframework.data.domain.Page;

public interface BookingService {
    Page<Booking> findBookings(int page);
    Booking findById(int id);
    Booking createBooking(BookingRequest request);
    Booking confirmedBooking(int id);
    Booking updateBooking(int id, BookingRequest request);
    Booking changeStatus(int id, String newStatus);
}
