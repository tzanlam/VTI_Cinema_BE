package cinema.service.Seat;


import cinema.modal.entity.Room;
import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.StatusSeat;
import cinema.modal.entity.constant.TypeSeat;
import cinema.modal.request.SeatRequest;
import cinema.repository.RoomRepository;
import cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Seat> findSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(int id) {
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public List<Seat> createSeat(SeatRequest request) throws Exception {
        List<Seat> seats = new ArrayList<>();
        List<String> firstNames = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        int row = request.getRow();
        int seatPerRow = request.getSeatPerRow();
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(()->new Exception("Room not found"));
        TypeSeat type = TypeSeat.valueOf(request.getType()); // Loại ghế

        if (row > firstNames.size()) {
            throw new IllegalArgumentException("Số hàng vượt quá giới hạn cho phép (A - J).");
        }
        for (int i = 0; i < row; i++) {
            String rowName = firstNames.get(i);
            for (int j = 1; j <= seatPerRow; j++) {
                Seat seat = new Seat();
                seat.setName(rowName + j);
                seat.setRoom(room);
                seat.setSeatType(type);
                seat.setPrice(type.getPrice());
                seat.setStatus(StatusSeat.AVAILABLE);
                seatRepository.save(seat);
                seats.add(seat);
            }
        }

        return seats;
    }
}
