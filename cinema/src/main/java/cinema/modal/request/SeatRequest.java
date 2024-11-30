package cinema.modal.request;

import lombok.Data;


@Data
public class SeatRequest {
    private int row;
    private int seatPerRow;
    private int roomId;
    private String type;
}