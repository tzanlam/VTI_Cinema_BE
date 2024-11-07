package cinema.service.Seat;


import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.SeatType;
import cinema.modal.request.SeatRequest;
import cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SeatServiceimpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Override
    public List<Seat> findSeats() {
        return List.of((Seat) seatRepository.findAll());
    }

    @Override
    public Seat findById(int id) {
        return seatRepository.findById(id).get();
    }

    @Override
    public Seat createSeat(SeatRequest request) {
        Seat seat = request.asSeat();
        seatRepository.save(seat);
        return seat;
    }

    @Override
    public Seat updateSeat(int id, SeatRequest request) {
        Seat seat = seatRepository.findById(id).orElse(null);
        if (seat != null) {
            Seat a = request.updateSeat(seat);
            seatRepository.save(a);
            return a;
        }
        return null;
    }

    @Override
    public Seat changeStatus(int id, String newStatus) {
        Seat seat = seatRepository.findById(id).orElse(null);
        if (seat != null) {
            List<SeatType> validStatuses = Arrays.asList(SeatType.STANDARD, SeatType.VIP, SeatType.DOUBLE);

            if (validStatuses.contains(newStatus)) {
                seat.setSeatType(SeatType.valueOf(newStatus));
                seatRepository.save(seat);
                return seat;
            } else {
                throw new IllegalArgumentException("Trạng thái không hợp lệ");
            }
        } else {
            System.out.println("không tìm thấy ghế với ID: " + id);
        }
        return null;
    }
}
