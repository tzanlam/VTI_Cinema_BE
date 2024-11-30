package cinema.modal.request;

import lombok.Data;

@Data
public class TicketRequest {
    private int accountId;
    private int seatId;
    private int showTimeId;
}
