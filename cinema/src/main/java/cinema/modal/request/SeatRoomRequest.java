package cinema.modal.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SeatRoomRequest {
    @NotNull
    private int rowQuantity;
    @NotNull
    private int room;
    private String typeSeat;
}
