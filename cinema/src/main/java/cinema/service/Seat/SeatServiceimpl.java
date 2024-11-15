package cinema.service.Seat;


import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.TypeSeat;
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
        List<Seat> seats = seatRepository.findAll();
        return seats;
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
    public Seat changeType(int id, String type) {
        Seat seat = seatRepository.findById(id).orElse(null);
        if (seat != null) {
            try {
                TypeSeat seatTypeEnum = TypeSeat.valueOf(type);
                List<TypeSeat> validStatuses = Arrays.asList(TypeSeat.STANDARD, TypeSeat.VIP, TypeSeat.DOUBLE);
                if (validStatuses.contains(seatTypeEnum)) {
                    seat.setSeatType(seatTypeEnum);
                    seatRepository.save(seat);
                    return seat;
                } else {
                    throw new IllegalArgumentException("SeatType không hợp lệ");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("SeatType không hợp lệ: " + type, e);
            }
        } else {
            System.out.println("Không tìm thấy ghế với ID: " + id);
        }
        return null;
    }

}
