package cinema.modal.entity.constant;

import lombok.Getter;

@Getter
public enum TypeSeat {
    SINGLE(100),
    VIP(150),
    DOUBLE(200);
    private final double price;
    TypeSeat(double price) {
        this.price = price;
    }
}
