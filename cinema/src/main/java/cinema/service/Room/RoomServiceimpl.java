package cinema.service.Room;

import cinema.modal.entity.Cinema;
import cinema.modal.entity.Room;
import cinema.modal.entity.constant.ScreenType;
import cinema.modal.entity.constant.StatusRoom;
import cinema.modal.request.RoomRequest;
import cinema.repository.CinemaRepository;
import cinema.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceimpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Override
    public List<Room> findRooms() {
        return List.of((Room) roomRepository.findAll());
    }

    @Override
    public Room findById(int id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public Room createRoom(RoomRequest request) throws Exception {
        Room room = new Room();
        room.setName(request.getName());
        Cinema cinema = cinemaRepository.findById(request.getCinema())
                .orElseThrow(() -> new Exception("Cinema not found"));
        room.setCinema(cinema);
        try {
            room.setStatus(StatusRoom.valueOf(request.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new Exception("error with status");
        }
        try {
            room.setScreenType(ScreenType.valueOf(request.getScreenType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new Exception("error with type");
        }
        roomRepository.save(room);
        return room;
    }

    @Override
    public Room updateRoom(int id, RoomRequest request) throws Exception {
        Room room = roomRepository.findById(id).get();
        if (room != null) {
            room.setName(request.getName());
            Cinema cinema = cinemaRepository.findById(request.getCinema()).get();
            room.setCinema(cinema);
            List<StatusRoom> valueStatus = Arrays.asList(StatusRoom.ACTIVE, StatusRoom.INACTIVE);
            if (valueStatus.contains(request.getStatus())) {
                room.setStatus(StatusRoom.valueOf(request.getStatus()));
            }
            else {
                throw new Exception("Error with Status Room");
            }
            List<ScreenType> valueScreen = Arrays.asList(ScreenType.FULL_SCREEN, ScreenType.FULL_SCREEN);
            if (valueScreen.contains(request.getScreenType())) {
                room.setScreenType(ScreenType.valueOf(request.getScreenType()));
            }
            else {
                throw new Exception("Error with Screen Type");
            }
            roomRepository.save(room);
            return room;
        }
        else {
            throw new Exception("Error with Id");
        }
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
