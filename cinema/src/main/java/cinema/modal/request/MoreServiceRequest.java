package cinema.modal.request;

import lombok.Data;

@Data
public class MoreServiceRequest {
    private String name;
    private String image;
    private String decription;
    private double price;
    private String status;
}
