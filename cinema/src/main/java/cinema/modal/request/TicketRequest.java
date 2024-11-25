package cinema.modal.request;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequest {
    private int account;
    private int seatRoom;
    private List<String> seat_select;
    private int showTime;

    // room - seat(loại với giá) seatroom quantity row seat 100 10 => 1/ 10ghe
}
