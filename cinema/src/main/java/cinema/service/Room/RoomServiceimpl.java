package cinema.service.Room;

import cinema.modal.entity.Cinema;
import cinema.modal.entity.Room;
import cinema.modal.entity.constant.ScreenType;
import cinema.modal.entity.constant.StatusRoom;
import cinema.modal.request.RoomRequest;
import cinema.repository.CinemaRepository;
import cinema.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Room> findRooms(int page) {
        return roomRepository.findAll(PageRequest.of(page, 10));
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
        Room room = roomRepository.findById(id).orElseThrow(() -> new Exception("Error with Id"));

        room.setName(request.getName());

        Cinema cinema = cinemaRepository.findById(request.getCinema())
                .orElseThrow(() -> new Exception("Error with Cinema Id"));
        room.setCinema(cinema);

        try {
            room.setStatus(StatusRoom.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new Exception("Error with Status Room");
        }

        try {
            room.setScreenType(ScreenType.valueOf(request.getScreenType()));
        } catch (IllegalArgumentException e) {
            throw new Exception("Error with Screen Type");
        }

        roomRepository.save(room);
        return room;
    }


    @Override
    public Room changeStatus(int id, String newStatus) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            try {
                StatusRoom statusEnum = StatusRoom.valueOf(newStatus);
                List<StatusRoom> validStatuses = Arrays.asList(StatusRoom.ACTIVE, StatusRoom.INACTIVE);
                if (validStatuses.contains(statusEnum)) {
                    room.setStatus(statusEnum);
                    roomRepository.save(room);
                    return room;
                } else {
                    throw new IllegalArgumentException("Trạng thái không hợp lệ");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Trạng thái không hợp lệ: " + newStatus, e);
            }
        } else {
            System.out.println("Không tìm thấy phòng chiếu với ID: " + id);
        }
        return null;
    }

}
