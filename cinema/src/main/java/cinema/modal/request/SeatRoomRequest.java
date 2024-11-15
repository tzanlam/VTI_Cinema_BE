package cinema.modal.request;

import cinema.modal.entity.Seat;
import cinema.modal.entity.SeatRoom;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class SeatRoomRequest {
    @NotNull
    private int room;
    @Min(1)
    @Max(4)
    private int rowStandard;
    @Min(1)
    @Max(19)
    private int standardSeat;

    @Min(1)
    @Max(4)
    private int rowVip;
    @Min(1)
    @Max(19)
    private int vipSeat;

    @Min(0)
    @Max(2)
    private int rowDouble;
    @Min(1)
    @Max(19)
    private int doubleSeat;

}
