package cinema.modal.request;

import cinema.modal.entity.Booking;
import lombok.Data;

@Data
public class BookingRequest {
    private int account;
    private int ticket;
    private int moreService;
    private int voucher;
    private int payment;
}
