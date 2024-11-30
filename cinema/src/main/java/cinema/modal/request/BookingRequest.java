package cinema.modal.request;

import lombok.Data;

@Data
public class BookingRequest {
    private int ticketId;
    private int moreServiceId;
    private int voucherId;
}
