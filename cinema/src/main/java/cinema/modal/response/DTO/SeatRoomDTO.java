package cinema.modal.response.DTO;

import cinema.modal.entity.SeatRoom;

public class SeatRoomDTO {
    private String seat_room_id;
    private String room_id;
    private String seat_id;
    private String seat_room_status;

    public SeatRoomDTO(SeatRoom seatRoom) {
        this.seat_room_id = String.valueOf(seatRoom.getId());
        this.room_id = String.valueOf(seatRoom.getRoom());
        this.seat_id = String.valueOf(seatRoom.getSeat());
        this.seat_room_status = String.valueOf(seatRoom.getStatusSeatroom());
    }
}
