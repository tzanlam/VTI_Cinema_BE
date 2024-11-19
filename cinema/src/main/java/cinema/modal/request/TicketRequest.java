package cinema.modal.request;

import lombok.Data;

@Data
public class TicketRequest {
    private int account;
    private int seatRoom;
    private int showTime;

    // room - seat(loại với giá) seatroom quantity row seat 100 10 => 1/ 10ghe
}
