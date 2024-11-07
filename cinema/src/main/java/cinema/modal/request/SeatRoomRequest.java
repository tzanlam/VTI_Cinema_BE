package cinema.modal.request;

import cinema.modal.entity.Room;
import cinema.modal.entity.Seat;
import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.constant.StatusSeatroom;
import lombok.Data;

@Data
public class SeatRoomRequest {
    private Room room;
    private Seat seat;
    private StatusSeatroom statusSeatroom;

    public SeatRoom asSeatRoom() {
        SeatRoom seatRoom = new SeatRoom();
        seatRoom.setRoom(room);
        seatRoom.setSeat(seat);
        seatRoom.setStatusSeatroom(statusSeatroom);
        return seatRoom;
    }

    public SeatRoom updateSeatRoom(SeatRoom seatRoom) {

        seatRoom.setRoom(room);
        seatRoom.setSeat(seat);
        seatRoom.setStatusSeatroom(statusSeatroom);
        return seatRoom;
    }
}
