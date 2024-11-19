package cinema.service.Payment;

import cinema.modal.entity.Payment;
import cinema.modal.request.PaymentRequest;
import cinema.repository.BookingRepository;
import cinema.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Payment> findPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment createPayment(PaymentRequest request) {
        Payment payment = request.asPayment();
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment updatePayment(int id, PaymentRequest request) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            Payment updatedPayment = request.updatePayment(payment);
            paymentRepository.save(updatedPayment);
            return updatedPayment;
        }
        return null;
    }
}
