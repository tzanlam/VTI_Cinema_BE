package cinema.modal.response.DTO;

import cinema.modal.entity.Booking;
import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class BookingDTO {
    private String booking_id;
    private String account_name;
    private List<String> ticket;
    private String more_service_name;
    private String voucher_name;
    private String total_price;
    private String booking_status;

    public BookingDTO(Booking booking) {
        this.booking_id = String.valueOf(booking.getId());
        this.account_name = booking.getAccount().getFullname();
        this.ticket = Collections.singletonList(String.valueOf(booking.getTicket()));
        this.more_service_name = booking.getMoreService().getName();
        this.voucher_name = booking.getVoucher().getName();
        this.total_price = String.valueOf(booking.getTotalPrice());
        this.booking_status = String.valueOf(booking.getStatus());
    }
}
