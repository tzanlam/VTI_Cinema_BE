package cinema.modal.request;

import cinema.modal.entity.Room;
import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.SeatType;
import lombok.Data;

@Data
public class SeatRequest {
    private String name;
    private String seat_number;
    private SeatType seatType;
    private double price;
    public Seat asSeat() {
        Seat seat = new Seat();
        seat.setName(name);
        seat.setSeat_number(seat_number);
        seat.setSeatType(seatType);
        seat.setPrice(price);
        return seat;
    }
    public Seat updateSeat(Seat seat) {
        seat.setName(name);
        seat.setSeat_number(seat_number);
        seat.setSeatType(seatType);
        seat.setPrice(price);
        return seat;
    }
}
