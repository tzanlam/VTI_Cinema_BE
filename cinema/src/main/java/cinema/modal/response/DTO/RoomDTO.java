package cinema.modal.response.DTO;

import cinema.modal.entity.Room;
import lombok.Data;

@Data
public class RoomDTO {
    private String room_id;
    private String room_name;
    private String cinema_id;
    private String status;
    private String screen_type;

    public RoomDTO(Room room) {
        this.room_id = String.valueOf(room.getId());
        this.room_name = room.getName();
        this.cinema_id = String.valueOf(room.getCinema().getName());
        this.status = String.valueOf(room.getStatus());
        this.screen_type = String.valueOf(room.getScreenType());
    }
}
