package cinema.service.SeatRoom;


import cinema.modal.entity.SeatRoom;

import cinema.modal.entity.constant.StatusSeatroom;

import cinema.modal.request.SeatRoomRequest;

import cinema.repository.SeatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SeatRoomServiceimpl implements SeatRoomService {
    @Autowired
    private SeatRoomRepository seatRoomRepository;
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
        SeatRoom seatRoom = request.asSeatRoom();
        seatRoomRepository.save(seatRoom);
        return seatRoom;
    }

    @Override
    public SeatRoom updateSeatRoom(int id, SeatRoomRequest request) {
        SeatRoom seatRoom = seatRoomRepository.findById(id).orElse(null);
        if (seatRoom != null) {
            SeatRoom a = request.updateSeatRoom(seatRoom);
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
}
