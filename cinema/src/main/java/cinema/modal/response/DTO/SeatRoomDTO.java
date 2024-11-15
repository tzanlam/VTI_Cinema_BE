package cinema.modal.response.DTO;

import cinema.modal.entity.SeatRoom;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class SeatRoomDTO {
    private String seat_room_id;
    private List<String> seat_id;
    private String seat_room_status;

    public SeatRoomDTO(SeatRoom seatRoom) {
        this.seat_room_id = String.valueOf(seatRoom.getId());
        this.seat_id = Collections.singletonList(String.valueOf(seatRoom.getSeats()));
        this.seat_room_status = String.valueOf(seatRoom.getStatusSeatroom());
    }
}
