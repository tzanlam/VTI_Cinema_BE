package cinema.controller;

import cinema.modal.entity.Voucher;
import cinema.modal.request.VoucherRequest;
import cinema.modal.response.DTO.VoucherDTO;
import cinema.service.Voucher.VoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> find() {
        try{
            List<Voucher> vouchers = voucherService.findVoucher();
            List<VoucherDTO> voucherDTOS = vouchers.stream()
                    .map(VoucherDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(voucherDTOS);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            Voucher voucher = voucherService.findVoucherById(id);
            VoucherDTO voucherDTO = new VoucherDTO(voucher);
            return ResponseEntity.ok(voucherDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findEffective")
    public ResponseEntity<?> findEffective() {
        try{
            List<Voucher> vouchers = voucherService.findEffectiveVouchers();
            List<VoucherDTO> voucherDTOS = vouchers.stream()
                    .map(VoucherDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(voucherDTOS);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody VoucherRequest request) {
        try{
            Voucher voucher = voucherService.createVoucher(request);
            VoucherDTO voucherDTO = new VoucherDTO(voucher);
            return ResponseEntity.ok(voucherDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody VoucherRequest request) {
        try {
            Voucher voucher = voucherService.updateVoucher(id,request);
            VoucherDTO voucherDTO = new VoucherDTO(voucher);
            return ResponseEntity.ok(voucherDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
