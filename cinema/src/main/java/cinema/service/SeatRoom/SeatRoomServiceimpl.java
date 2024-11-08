package cinema.service.SeatRoom;


import cinema.modal.entity.Room;
import cinema.modal.entity.Seat;
import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.constant.StatusSeatroom;
import cinema.modal.request.SeatRoomRequest;
import cinema.repository.RoomRepository;
import cinema.repository.SeatRepository;
import cinema.repository.SeatRoomRepository;
import cinema.service.Room.RoomService;
import cinema.service.Seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SeatRoomServiceimpl implements SeatRoomService {
    @Autowired
    private SeatRoomRepository seatRoomRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<SeatRoom> findSeatRooms() {
        return List.of((SeatRoom) seatRoomRepository.findAll());
    }

    @Override
    public SeatRoom findById(int id) {
        return seatRoomRepository.findById(id).get();
    }

    @Override
    public SeatRoom createSeatRoom(SeatRoomRequest request) {
        SeatRoom seatRoom = populate(request);
        seatRoom.setStatusSeatroom(StatusSeatroom.AVAILABLE);
        seatRoomRepository.save(seatRoom);
        return seatRoom;
    }

    @Override
    public SeatRoom updateSeatRoom(int id, SeatRoomRequest request) {
        SeatRoom seatRoom = seatRoomRepository.findById(id).orElse(null);
        if (seatRoom != null) {
            SeatRoom a = populate(request);
            a.setId(seatRoom.getId());
            seatRoomRepository.save(a);
            return a;
        }
        return null;
    }

    @Override
    public SeatRoom changeStatus(int id, String newStatus) {
        SeatRoom seatRoom = seatRoomRepository.findById(id).orElse(null);
        if (seatRoom != null) {
            List<StatusSeatroom> validStatuses = Arrays.asList(StatusSeatroom.AVAILABLE, StatusSeatroom.BOOKED);
            if (validStatuses.contains(newStatus)) {
                seatRoom.setStatusSeatroom(StatusSeatroom.valueOf(newStatus));
                seatRoomRepository.save(seatRoom);
                return seatRoom;
            } else {
                throw new IllegalArgumentException("Trạng thái không hợp lệ");
            }
        } else {
            System.out.println("Không tìm thấy phòng chiếu + ghế với ID: " + id);
        }
        return null;
    }

    private SeatRoom populate(SeatRoomRequest request) {
        SeatRoom seatRoom = new SeatRoom();
        Room room = roomRepository.findById(request.getRoom()).orElse(null);
        if (room != null) {
            Seat seat = seatRepository.findById(request.getSeat()).orElse(null);
            if (seat != null) {
                seatRoom.setRoom(room);
                seatRoom.setSeat(seat);
                return seatRoom;
            }
        }
        return null;
    }
}
