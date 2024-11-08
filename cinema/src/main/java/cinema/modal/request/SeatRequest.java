package cinema.modal.request;

import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.SeatType;
import lombok.Data;

@Data
public class SeatRequest {
    private String name;
    private String seatType;
    private double price;

    public Seat asSeat() {
        Seat seat = new Seat();
        return populateSeat(seat);
    }

    public Seat updateSeat(Seat seat) {
        Seat updatedSeat = populateSeat(seat);
        updatedSeat.setId(seat.getId());
        return updatedSeat;
    }

    private Seat populateSeat(Seat seat) {
        seat.setName(name);
        if (seatType == null) {
            throw new IllegalArgumentException("seatType không được để trống.");
        }
        try {
            SeatType type = SeatType.valueOf(seatType.toUpperCase());
            seat.setSeatType(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Giá trị seatType không hợp lệ: " + seatType);
        }

        seat.setPrice(price);
        return seat;
    }
}
