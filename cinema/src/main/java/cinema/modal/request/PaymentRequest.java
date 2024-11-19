package cinema.modal.request;

import cinema.modal.entity.Payment;
import cinema.modal.entity.constant.TypePayment;
import cinema.modal.entity.constant.StatusPayment;
import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private String method;
    private String addressTransfer;
    private String qrCode;
    private String status;

    public Payment asPayment() {
        Payment payment = new Payment();
        populatePayment(payment);
        return payment;
    }

    public Payment updatePayment(Payment payment) {
        populatePayment(payment);
        return payment;
    }

    private void populatePayment(Payment payment) {
        List<TypePayment> methodPayments = List.of(TypePayment.values());
        TypePayment methodPayment = TypePayment.valueOf(this.method);
        if (methodPayments.contains(methodPayment)) {
            payment.setMethod(methodPayment);
        }
        payment.setAddressTransfer(addressTransfer);
        payment.setQrCode(qrCode);
        List<StatusPayment> statusPayments = List.of(StatusPayment.values());
        StatusPayment statusPayment = StatusPayment.valueOf(this.status);
        if (statusPayments.contains(statusPayment)){
            payment.setStatus(statusPayment);
        }
    }
}
