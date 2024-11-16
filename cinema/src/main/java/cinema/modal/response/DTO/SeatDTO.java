package cinema.modal.response.DTO;

import cinema.modal.entity.Seat;
import lombok.Data;

@Data
public class SeatDTO {
    private String seat_id;
    private String seat_name;
    private String seat_type;
    private String seat_price;

    public SeatDTO(Seat seat) {
        this.seat_id = String.valueOf(seat.getId());
        this.seat_name = seat.getName();
        this.seat_type = String.valueOf(seat.getSeatType());
        this.seat_price = String.valueOf(seat.getPrice());
    }
}
