package cinema.modal.response.DTO;

import cinema.modal.entity.Receipt;
import lombok.Data;

@Data
public class ReceiptDTO {
    private String receipt_id;
    private String receipt_type;
    private String booking;
    private String account;
    private String receipt_reason;
    private String receipt_amount;
    private String receipt_date;
    private String receipt_status;

    public ReceiptDTO(Receipt receipt) {
        this.receipt_id = String.valueOf(receipt.getId());
        this.receipt_type = String.valueOf(receipt.getType());
        this.booking = String.valueOf(receipt.getBooking());
        this.account = String.valueOf(receipt.getAccount());
        this.receipt_reason = String.valueOf(receipt.getReason());
        this.receipt_amount = String.valueOf(receipt.getAmount());
        this.receipt_status = String.valueOf(receipt.getStatus());
        this.receipt_date = String.valueOf(receipt.getCreatedDate());
    }
}
