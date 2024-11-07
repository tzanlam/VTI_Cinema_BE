package cinema.service.Room;

import cinema.modal.entity.Movie;
import cinema.modal.entity.Room;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.entity.constant.StatusRoom;
import cinema.modal.request.RoomRequest;
import cinema.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceimpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> findRooms() {
        return List.of((Room) roomRepository.findAll());
    }

    @Override
    public Room findById(int id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public Room createRoom(RoomRequest request) {
        Room room = request.asRoom();
        roomRepository.save(room);
        return room;
    }

    @Override
    public Room updateRoom(int id, RoomRequest request) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            Room a = request.updateRoom(room);
            roomRepository.save(a);
            return a;
        }
        return null;
    }

    @Override
    public Room changeStatus(int id, String newStatus) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            List<StatusRoom> validStatuses = Arrays.asList(StatusRoom.ACTIVE, StatusRoom.INACTIVE);

            if (validStatuses.contains(newStatus)) {
                room.setStatus(StatusRoom.valueOf(newStatus));
                roomRepository.save(room);
                return room;
            } else {
                throw new IllegalArgumentException("Trạng thái không hợp lệ");
            }
        } else {
            System.out.println("Không tìm thấy phòng chiếu với ID: " + id);
        }
        return null;
    }
}
