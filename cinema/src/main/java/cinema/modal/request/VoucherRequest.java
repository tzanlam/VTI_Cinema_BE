package cinema.modal.request;

import cinema.modal.entity.Voucher;
import cinema.modal.entity.constant.StatusVoucher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static cinema.util.ConvertDateTime.convertToLocalDate;

@Data
public class VoucherRequest {
    private String name;
    private String description;
    private String discount;
    private String expiry;
    private String status;

    public Voucher asVoucher() {
        Voucher voucher = new Voucher();
        populate(voucher);
        return voucher;
    }

    public Voucher updateVoucher(Voucher voucher) {
        Voucher v = populate(voucher);
        v.setId(voucher.getId());
        return v;
    }

    private Voucher populate(Voucher voucher) {
        voucher.setName(name);
        voucher.setDescription(description);
        voucher.setDiscount(Double.parseDouble(discount));
        voucher.setExpiry(convertToLocalDate(expiry));
        List<StatusVoucher> statusVouchers = List.of(StatusVoucher.values());
        if (statusVouchers.contains(status.toUpperCase())) {
            voucher.setStatus(StatusVoucher.valueOf(status));
        }
        return voucher;
    }
}
