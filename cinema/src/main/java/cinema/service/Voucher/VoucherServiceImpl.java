package cinema.service.Voucher;

import cinema.modal.entity.Voucher;
import cinema.modal.entity.constant.StatusVoucher;
import cinema.modal.request.VoucherRequest;
import cinema.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;
    @Override
    public Page<Voucher> findVoucher(int page) {
        return voucherRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Voucher findVoucherById(int id) {
        return voucherRepository.findById(id).orElse(null);
    }

    @Override
    public Voucher createVoucher(VoucherRequest request) {
        Voucher voucher = request.asVoucher();
        voucherRepository.save(voucher);
        return voucher;
    }

    @Override
    public Voucher updateVoucher(int id, VoucherRequest request) {
        Voucher voucher = findVoucherById(id);
        if (voucher != null) {
            Voucher v = request.updateVoucher(voucher);
            voucherRepository.save(v);
            return v;
        }
        return null;
    }

    @Override
    public List<Voucher> findVoucherEffective() {
        List<Voucher> vouchers = voucherRepository.findAll();
        List<Voucher> effectiveVouchers = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            if (voucher.getStatus().equals(StatusVoucher.EFFECTIVE)){
                effectiveVouchers.add(voucher);
            }
        }
        return effectiveVouchers;
    }
}
