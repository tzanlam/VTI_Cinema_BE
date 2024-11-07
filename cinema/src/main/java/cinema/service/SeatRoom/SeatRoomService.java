package cinema.service.SeatRoom;

import cinema.modal.entity.SeatRoom;
import cinema.modal.request.SeatRoomRequest;

import java.util.List;

public interface SeatRoomService {
    List<SeatRoom> findSeatRooms();
    SeatRoom findById(int id);
    SeatRoom createSeatRoom(SeatRoomRequest request);
    SeatRoom updateSeatRoom(int id, SeatRoomRequest request);
    SeatRoom changeStatus(int id, String status);
}
