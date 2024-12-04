package cinema.modal.response.DTO;

import cinema.modal.entity.Seat;
import lombok.Data;

@Data
public class SeatDTO {
    private String seat_id;
    private String seat_name;
    private String cinema;
    private String room;
    private String seat_type;
    private String seat_price;
    private String seat_status;

    public SeatDTO(Seat seat) {
        this.seat_id = String.valueOf(seat.getId());
        this.seat_name = seat.getName();
        this.cinema = String.valueOf(seat.getRoom().getCinema());
        this.room = String.valueOf(seat.getRoom());
        this.seat_type = String.valueOf(seat.getType());
        this.seat_price = String.valueOf(seat.getPrice());
        this.seat_status = String.valueOf(seat.getStatus());
    }
}
