package cinema.modal.request;

import lombok.Data;

@Data
public class UserPaymentRequest {
    private String nameBank;
    private String cardNumber;
    private String amount;
}
