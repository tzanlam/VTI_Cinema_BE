package cinema.modal.request;

import lombok.Data;

@Data
public class RoomRequest {
    private String name;
    private int cinema;
    private String status;
    private String screenType;
}
