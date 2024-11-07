package cinema.service.Room;


import cinema.modal.entity.Room;
import cinema.modal.request.RoomRequest;

import java.util.List;

public interface RoomService {
    List<Room> findRooms();
    Room findById(int id);
    Room createRoom(RoomRequest request);
    Room updateRoom(int id, RoomRequest request);
    Room changeStatus(int id, String status);
}
