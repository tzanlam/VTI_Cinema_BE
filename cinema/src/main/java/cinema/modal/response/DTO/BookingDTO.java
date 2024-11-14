package cinema.modal.response.DTO;

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
}
