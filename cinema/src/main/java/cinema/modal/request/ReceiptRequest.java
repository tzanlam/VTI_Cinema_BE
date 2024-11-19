package cinema.modal.request;

import lombok.Data;

@Data
public class ReceiptRequest {
    private String typeReceipt;
    private int account;
    private int booking;
    private String reason;
    private String amount;
    private String status;
}
