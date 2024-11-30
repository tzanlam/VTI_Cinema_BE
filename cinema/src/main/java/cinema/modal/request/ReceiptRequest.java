package cinema.modal.request;

import lombok.Data;

@Data
public class ReceiptRequest {
    private String type;
    private int account;
    private int booking;
    private String reason;
    private String amount;
}
