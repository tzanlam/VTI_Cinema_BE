package cinema.modal.response.DTO;

import cinema.modal.entity.Voucher;

import java.util.List;

public class VoucherDTO {
    private String voucher_name;
    private String voucher_decription;
    private String discount;
    private String expiry;
    private String voucher_status;

    public VoucherDTO(Voucher voucher) {
        this.voucher_name = String.valueOf(voucher.getId());
        this.voucher_decription = voucher.getDescription();
        this.discount = String.valueOf(voucher.getDiscount());
        this.expiry = String.valueOf(voucher.getExpiry());
        this.voucher_status = String.valueOf(voucher.getStatus());
    }
}
