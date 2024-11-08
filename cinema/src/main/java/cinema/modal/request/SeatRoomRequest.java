package cinema.modal.request;

import cinema.modal.entity.Room;
import cinema.modal.entity.Seat;
import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.constant.StatusSeatroom;
import lombok.Data;

@Data
public class SeatRoomRequest {
    private int room;
    private int seat;
    private String statusSeatroom;
}
