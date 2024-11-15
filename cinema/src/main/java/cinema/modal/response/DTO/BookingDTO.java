package cinema.modal.response.DTO;

import cinema.modal.entity.Booking;
import lombok.Data;

@Data
public class BookingDTO {
    private String booking_id;
    private String account_id;
    private String ticket_id;
    private String more_service_id;
    private String voucher_id;
    private String total_price;
    private String payment_id;
    private String booking_status;

    public BookingDTO(Booking booking) {
        this.booking_id = String.valueOf(booking.getId());
        this.account_id = String.valueOf(booking.getAccount().getId());
        this.ticket_id = booking.getTicket() != null ? String.valueOf(booking.getTicket().getId()) : null;
        this.more_service_id = booking.getMoreService() != null ? String.valueOf(booking.getMoreService().getId()) : null;
        this.voucher_id = booking.getVoucher() != null ? String.valueOf(booking.getVoucher().getId()) : null;
        this.total_price = String.valueOf(booking.getTotalPrice());
        this.payment_id = booking.getPayment() != null ? String.valueOf(booking.getPayment().getId()) : null;
        this.ticket_id = String.valueOf(booking.getTicket().getId());
        this.more_service_id = String.valueOf(booking.getMoreService().getId());
        this.voucher_id = String.valueOf(booking.getVoucher().getId());
        this.total_price = String.valueOf(booking.getTotalPrice());
        this.payment_id = String.valueOf(booking.getPayment().getId());
        this.booking_status = String.valueOf(booking.getStatus());
    }
}
