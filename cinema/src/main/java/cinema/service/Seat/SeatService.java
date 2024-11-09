package cinema.service.Seat;


import cinema.modal.entity.Seat;

import cinema.modal.request.SeatRequest;

import java.util.List;

public interface SeatService {
    List<Seat> findSeats();
    Seat findById(int id);
    Seat createSeat(SeatRequest request);
    Seat updateSeat(int id, SeatRequest request);
    Seat changeType(int id, String type);
}
