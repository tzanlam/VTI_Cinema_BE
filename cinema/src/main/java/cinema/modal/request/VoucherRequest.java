package cinema.modal.request;

import cinema.modal.entity.Voucher;
import cinema.modal.entity.constant.StatusVoucher;
import lombok.Data;

import static cinema.util.ConvertDateTime.convertToLocalDate;

@Data
public class VoucherRequest {
    private String name;
    private String description;
    private String discount;
    private String expiry;
    private String quantity;

    public Voucher asVoucher() {
        Voucher voucher = new Voucher();
        populate(voucher);
        voucher.setStatus(StatusVoucher.EFFECTIVE);
        return voucher;
    }

    public Voucher updateVoucher(Voucher voucher) {
        populate(voucher);
        return voucher;
    }

    private void populate(Voucher voucher) {
        voucher.setName(name);
        voucher.setDescription(description);
        voucher.setDiscount(Double.parseDouble(discount));
        voucher.setQuantity(Integer.parseInt(quantity));
        voucher.setExpiry(convertToLocalDate(expiry));
    }
}
