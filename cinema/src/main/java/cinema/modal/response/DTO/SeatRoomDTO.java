package cinema.modal.response.DTO;

import cinema.modal.entity.SeatRoom;
import lombok.Data;

import java.util.List;

@Data
public class SeatRoomDTO {
    private String seat_room_id;
    private String room_name;
    private String cinema_name;
    private List<String> list_seats;
    private String type_seat;
    private String status_seat;

    public SeatRoomDTO(SeatRoom seatRoom) {
        this.seat_room_id = String.valueOf(seatRoom.getId());
        this.room_name = String.valueOf(seatRoom.getRoom().getName());
        this.cinema_name = String.valueOf(seatRoom.getRoom().getCinema().getName());
        this.list_seats = seatRoom.getRowNames();
        this.type_seat  = seatRoom.getTypeSeat().toString();
        this.status_seat = seatRoom.getStatus().toString();
    }
}
