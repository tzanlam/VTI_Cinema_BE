package cinema.modal.request;

import com.google.firebase.database.annotations.NotNull;

import lombok.Data;

@Data
public class SeatRoomRequest {

    @NotNull
    private int rowQuantity;
    private int seatQuantity;
    @NotNull
    private int room;
    private String typeSeat;
}
