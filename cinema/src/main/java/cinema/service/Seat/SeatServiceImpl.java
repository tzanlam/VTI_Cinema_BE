package cinema.service.Seat;


import cinema.modal.entity.Seat;
import cinema.modal.request.SeatRequest;
import cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Override
    public List<Seat> findSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(int id) {
        return seatRepository.findById(id).orElse(null);
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
}
