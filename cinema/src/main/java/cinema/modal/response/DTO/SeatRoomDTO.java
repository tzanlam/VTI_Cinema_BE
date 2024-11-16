package cinema.modal.response.DTO;

import lombok.Data;

@Data
public class SeatRoomDTO {
    private String seat_room_id;
    private String row_quantity;
    private String seat_quantity;
    private String room;
    private String type_seat;

    public SeatRoomDTO(SeatRoomDTO seatRoomDTO) {
        this.seat_room_id = String.valueOf(seatRoomDTO.getSeat_room_id());
        this.row_quantity = String.valueOf(seatRoomDTO.getRow_quantity());
        this.seat_quantity = String.valueOf(seatRoomDTO.getSeat_quantity());
        this.room = String.valueOf(seatRoomDTO.getRoom());
        this.type_seat = String.valueOf(seatRoomDTO.getType_seat());
    }
}
