package cinema.service.Seat;


import cinema.modal.entity.Seat;

import cinema.modal.request.SeatRequest;

import java.util.List;

public interface SeatService {
    List<Seat> findSeats();
    Seat findById(int id);
    List<Seat> createSeat(SeatRequest request) throws Exception;
}
