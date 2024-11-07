package cinema.modal.request;

import cinema.modal.entity.Cinema;
import cinema.modal.entity.Room;
import cinema.modal.entity.constant.ScreenType;
import cinema.modal.entity.constant.StatusRoom;
import lombok.Data;

@Data
public class RoomRequest {
    private String name;
    private Cinema cinema;
    private StatusRoom status;
    private ScreenType screenType;


    public Room asRoom() {
        Room room = new Room();
        room.setName(name);
        room.setCinema(cinema);
        room.setStatus(status);
        room.setScreenType(screenType);
        return room;
}

    public Room updateRoom(Room room) {
        room.setName(name);
        room.setCinema(cinema);
        room.setStatus(status);
        room.setScreenType(screenType);
        return room;
    }



}
