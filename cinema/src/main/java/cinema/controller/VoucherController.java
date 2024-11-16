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

    @GetMapping("/find/{page}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> find(@PathVariable int page) {
        try{
            List<Voucher> vouchers = (List<Voucher>) voucherService.findVoucher(page);
            List<VoucherDTO> voucherDTOS = vouchers.stream()
                    .map(voucher -> modelMapper.map(voucher,VoucherDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(voucherDTOS);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            Voucher voucher = voucherService.findVoucherById(id);
            VoucherDTO voucherDTO = modelMapper.map(voucher,VoucherDTO.class);
            return ResponseEntity.ok(voucherDTO);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findEffective")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> findEffective() {
        try{
            return ResponseEntity.ok(voucherService.findVoucherEffective());
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody VoucherRequest request) {
        try{
            Voucher voucher = voucherService.createVoucher(request);
            VoucherDTO voucherDTO = modelMapper.map(voucher,VoucherDTO.class);
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
            VoucherDTO voucherDTO = modelMapper.map(voucher,VoucherDTO.class);
            return ResponseEntity.ok(voucherDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
