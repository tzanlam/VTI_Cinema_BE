package cinema.modal.entity.constant;

public enum TypeSeat {
    STANDARD(100),
    VIP(150),
    DOUBLE(200);
    private final double price;
    TypeSeat(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
