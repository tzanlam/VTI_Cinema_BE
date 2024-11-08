package cinema.service.Room;


import cinema.modal.entity.Room;
import cinema.modal.request.RoomRequest;
import org.springframework.data.domain.Page;


public interface RoomService {
    Page<Room> findRooms(int page);
    Room findById(int id);
    Room createRoom(RoomRequest request) throws Exception;
    Room updateRoom(int id, RoomRequest request) throws Exception;
    Room changeStatus(int id, String status);
}
