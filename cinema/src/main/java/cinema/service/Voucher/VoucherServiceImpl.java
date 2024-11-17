package cinema.service.Voucher;

import cinema.modal.entity.Voucher;
import cinema.modal.entity.constant.StatusVoucher;
import cinema.modal.request.VoucherRequest;
import cinema.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> findVoucher() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher findVoucherById(int id) {
        return voucherRepository.findById(id).orElse(null);
    }

    @Override
    public Voucher createVoucher(VoucherRequest request) {
        Voucher voucher = request.asVoucher();
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(int id, VoucherRequest request) {
        return voucherRepository.findById(id)
                .map(existingVoucher -> {
                    Voucher updatedVoucher = request.updateVoucher(existingVoucher);
                    return voucherRepository.save(updatedVoucher);
                })
                .orElse(null);
    }

    @Override
    public List<Voucher> findEffectiveVouchers() {
        return voucherRepository.findAll().stream()
                .filter(voucher -> voucher.getStatus() == StatusVoucher.EFFECTIVE)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateVoucherStatus() {
        LocalDate today = LocalDate.now();
        List<Voucher> vouchers = voucherRepository.findAll();

        vouchers.stream()
                .filter(voucher -> voucher.getExpiry().isBefore(today) && voucher.getStatus() != StatusVoucher.EXPIRED)
                .forEach(voucher -> {
                    voucher.setStatus(StatusVoucher.EXPIRED);
                    voucherRepository.save(voucher);
                });
    }
}
