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
        // Danh sách ghế sẽ trả về
        List<Seat> seats = new ArrayList<>();

        // Lấy dữ liệu từ request
        int row = request.getRow(); // Số hàng
        int seatPerRow = request.getSeatPerRow(); // Số ghế mỗi hàng

        // Tìm phòng theo ID
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found for ID: " + request.getRoomId()));

        // Kiểm tra loại ghế hợp lệ
        TypeSeat type;
        try {
            type = TypeSeat.valueOf(request.getType());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid seat type: " + request.getType());
        }

        // Kiểm tra số hàng hợp lệ
        if (row <= 0 || row > 26) { // Giới hạn hàng từ A-Z
            throw new IllegalArgumentException("Number of rows must be between 1 and 26.");
        }

        // Tạo ghế
        for (int i = 0; i < row; i++) {
            char rowChar = (char) ('A' + i); // Tên hàng từ 'A'
            String rowName = String.valueOf(rowChar);
            for (int j = 1; j <= seatPerRow; j++) {
                Seat seat = new Seat();
                seat.setName(rowName + j); // Ví dụ: A1, A2
                seat.setRoom(room);
                seat.setType(type);
                seat.setPrice(type.getPrice());
                seat.setStatus(StatusSeat.AVAILABLE);
                seats.add(seat); // Thêm ghế vào danh sách
            }
        }

        // Lưu tất cả ghế vào cơ sở dữ liệu
        seatRepository.saveAll(seats);

        // Trả về danh sách ghế đã tạo
        return seats;
    }
}
