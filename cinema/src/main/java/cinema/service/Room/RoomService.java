package cinema.service.Room;


import cinema.modal.entity.Room;
import cinema.modal.request.RoomRequest;

import java.util.List;

public interface RoomService {
    List<Room> findRooms();
    Room findById(int id);
    Room createRoom(RoomRequest request) throws Exception;
    Room updateRoom(int id, RoomRequest request) throws Exception;
    Room changeStatus(int id, String status);
}
