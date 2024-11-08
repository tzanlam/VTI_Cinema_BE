package cinema.service.Payment;

import cinema.modal.entity.Payment;
import cinema.modal.request.PaymentRequest;

import java.util.List;

public interface PaymentService {
    List<Payment> findPayments();
    Payment findPaymentById(int id);
    Payment createPayment(PaymentRequest request);
    Payment updatePayment(int id, PaymentRequest request);

}
