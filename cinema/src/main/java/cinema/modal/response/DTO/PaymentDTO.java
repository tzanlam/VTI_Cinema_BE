package cinema.modal.response.DTO;

import cinema.modal.entity.Payment;
import lombok.Data;

@Data
public class PaymentDTO {
    private String payment_id;
    private String payment_method;
    private String address_transfer;
    private String qr_code;
    private String payment_status;

    public PaymentDTO(Payment payment) {
        this.payment_id = String.valueOf(payment.getId());
        this.payment_method = String.valueOf(payment.getType());
        this.address_transfer = payment.getAddress();
        this.payment_status = String.valueOf(payment.getStatus());
    }
}
