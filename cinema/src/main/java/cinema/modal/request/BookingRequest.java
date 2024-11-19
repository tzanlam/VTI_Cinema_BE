package cinema.modal.request;

import lombok.Data;

@Data
public class BookingRequest {
    private int ticket;
    private int moreService;
    private int voucher;
}
