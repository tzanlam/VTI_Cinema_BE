package cinema.modal.request;

import cinema.modal.entity.Payment;
import cinema.modal.entity.constant.MethodPayment;
import cinema.modal.entity.constant.StatusPayment;
import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private String methodPayment;
    private String addressTranfer;
    private String qrCode;
    private String status;

    public Payment asPayment() {
        Payment payment = new Payment();

        List<MethodPayment> methodPayments = List.of(MethodPayment.values());
        if (methodPayments.contains(methodPayment)) {
            payment.setMethod(MethodPayment.valueOf(String.valueOf(methodPayment)));
        }

        payment.setAddressTransfer(addressTranfer);
        payment.setQrCode(qrCode);

        List<StatusPayment> statusPayments = List.of(StatusPayment.values());
        if (statusPayments.contains(status)){
            payment.setStatus(StatusPayment.valueOf(String.valueOf(status)));
        }
        return payment;
    }

    public Payment updatePayment(Payment payment) {
        List<MethodPayment> methodPayments = List.of(MethodPayment.values());
        if (methodPayments.contains(methodPayment)) {
            payment.setMethod(MethodPayment.valueOf(String.valueOf(methodPayment)));
        }

        payment.setAddressTransfer(addressTranfer);
        payment.setQrCode(qrCode);

        List<StatusPayment> statusPayments = List.of(StatusPayment.values());
        if (statusPayments.contains(status)){
            payment.setStatus(StatusPayment.valueOf(String.valueOf(status)));
        }
        return payment;
    }
}
