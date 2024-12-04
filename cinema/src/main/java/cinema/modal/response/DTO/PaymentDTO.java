package cinema.modal.response.DTO;

import cinema.modal.entity.Payment;
import lombok.Data;

@Data
public class PaymentDTO {
    private String payment_id;
    private String payment_type;
    private String payment_address;
    private String payment_status;

    public PaymentDTO(Payment payment) {
        this.payment_id = String.valueOf(payment.getId());
        this.payment_type = String.valueOf(payment.getType());
        this.payment_address = payment.getAddress();
        this.payment_status = String.valueOf(payment.getStatus());
    }
}
