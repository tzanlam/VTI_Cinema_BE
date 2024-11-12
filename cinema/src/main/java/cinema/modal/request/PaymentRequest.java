package cinema.modal.request;

import cinema.modal.entity.Payment;
import cinema.modal.entity.constant.MethodPayment;
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

        List<MethodPayment> methodPayments = List.of(MethodPayment.values());
        MethodPayment methodPayment = MethodPayment.valueOf(this.method);
        if (methodPayments.contains(methodPayment)) {
            payment.setMethod(MethodPayment.valueOf(this.method));
        }

        payment.setAddressTransfer(addressTransfer);
        payment.setQrCode(qrCode);

        List<StatusPayment> statusPayments = List.of(StatusPayment.values());
        StatusPayment statusPayment = StatusPayment.valueOf(this.status);
        if (statusPayments.contains(statusPayment)) {
            payment.setStatus(StatusPayment.valueOf(String.valueOf(status)));
        }
        return payment;
    }

    public Payment updatePayment(Payment payment) {
        List<MethodPayment> methodPayments = List.of(MethodPayment.values());
        if (methodPayments.contains(method)) {
            payment.setMethod(MethodPayment.valueOf(String.valueOf(method)));
        }

        payment.setAddressTransfer(addressTransfer);
        payment.setQrCode(qrCode);

        List<StatusPayment> statusPayments = List.of(StatusPayment.values());
        if (statusPayments.contains(status)){
            payment.setStatus(StatusPayment.valueOf(String.valueOf(status)));
        }
        return payment;
    }
}
