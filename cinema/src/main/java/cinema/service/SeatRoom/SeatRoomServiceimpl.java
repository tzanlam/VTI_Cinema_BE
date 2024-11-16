package cinema.service.SeatRoom;


import cinema.modal.entity.Room;
import cinema.modal.entity.Seat;
import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.constant.StatusSeatRoom;
import cinema.modal.entity.constant.TypeSeat;
import cinema.modal.request.SeatRoomRequest;
import cinema.repository.RoomRepository;
import cinema.repository.SeatRepository;
import cinema.repository.SeatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<SeatRoom> seatRooms = seatRoomRepository.findAll();
        return seatRooms;
    }

    @Override
    public SeatRoom findById(int id) {
        return seatRoomRepository.findById(id).get();
    }

    @Override
    public SeatRoom createSeatRoom(SeatRoomRequest request) {
        SeatRoom seatRoom = new SeatRoom();
        populate(request, seatRoom);
        seatRoomRepository.save(seatRoom);
        return seatRoom;
    }

    @Override
    public SeatRoom updateSeatRoom(int id, SeatRoomRequest request) {
        SeatRoom seatRoom = seatRoomRepository.findById(id).orElse(null);
        if (seatRoom != null) {
            SeatRoom a = populate(request, seatRoom);
            a.setId(seatRoom.getId());
            seatRoomRepository.save(a);
            return a;
        }
        return null;
    }

    private SeatRoom populate(SeatRoomRequest request, SeatRoom seatRoom) {
        Room room = roomRepository.findById(request.getRoom()).orElse(null);
        List<Seat> seats = seatRepository.findBySeatType(TypeSeat.valueOf(request.getTypeSeat()));
        List<String> rowName = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        List<String> seatNames = new ArrayList<>();
        int i , j;
        for (Seat seat : seats) {
            for (i = 0; i < request.getRowQuantity(); i++) {
                for (j = 0; j < request.getSeatQuantity(); j++) {
                    seatNames.add(rowName.get(i) + seat.getName());
                }
            }
        }
        seatRoom.setRoom(room);
        seatRoom.setRowNames(seatNames);
        seatRoom.setTypeSeat(TypeSeat.valueOf(request.getTypeSeat()));
        seatRoom.setStatus(StatusSeatRoom.AVAILABLE);
        return seatRoom;
    }
}
