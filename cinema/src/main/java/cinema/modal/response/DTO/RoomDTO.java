package cinema.modal.response.DTO;

import cinema.modal.entity.Room;
import lombok.Data;

@Data
public class RoomDTO {
    private String room_id;
    private String room_name;
    private String cinema;
    private String room_status;
    private String room_screen_type;

    public RoomDTO(Room room) {
        this.room_id = String.valueOf(room.getId());
        this.room_name = room.getName();
        this.cinema = String.valueOf(new CinemaDTO(room.getCinema()));
        this.room_status = String.valueOf(room.getStatus());
        this.room_screen_type = String.valueOf(room.getScreenType());
    }
}
