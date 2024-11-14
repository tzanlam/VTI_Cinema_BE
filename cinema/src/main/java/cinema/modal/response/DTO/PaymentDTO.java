package cinema.modal.response.DTO;

import cinema.modal.entity.Payment;

public class PaymentDTO {
    private String payment_id;
    private String payment_method;
    private String address_transfer;
    private String qr_code;
    private String payment_status;

    public PaymentDTO(Payment payment) {
        this.payment_id = String.valueOf(payment.getId());
        this.payment_method = String.valueOf(payment.getMethod());
        this.address_transfer = payment.getAddressTransfer();
        this.qr_code = payment.getQrCode();
        this.payment_status = String.valueOf(payment.getStatus());
    }
}
