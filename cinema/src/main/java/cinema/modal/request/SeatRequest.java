package cinema.modal.request;

import cinema.modal.entity.Seat;
import cinema.modal.entity.constant.TypeSeat;
import lombok.Data;

@Data
public class SeatRequest {
    private String name;
    private String seatType;

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
        if (seatType == null || seatType.trim().isEmpty()) {
            throw new IllegalArgumentException("Giá trị seatType không được để trống.");
        }

        try {
            TypeSeat type = TypeSeat.valueOf(seatType.trim().toUpperCase());
            seat.setSeatType(type);
            seat.setPrice(type.getPrice());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Giá trị seatType không hợp lệ: " + seatType, e);
        }

        return seat;
    }
}