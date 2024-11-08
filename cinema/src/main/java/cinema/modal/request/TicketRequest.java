package cinema.modal.request;

import lombok.Data;

@Data
public class TicketRequest {
    private int seat;
    private int showTime;
    private int seat_quantity;
}
