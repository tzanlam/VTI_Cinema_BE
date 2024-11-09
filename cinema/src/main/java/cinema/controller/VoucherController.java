package cinema.controller;

import cinema.modal.request.VoucherRequest;
import cinema.service.Voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/find/{page}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> find(@PathVariable int page) {
        try{
            return ResponseEntity.ok(voucherService.findVoucher(page));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(voucherService.findVoucherById(id));
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
            return ResponseEntity.ok(voucherService.createVoucher(request));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody VoucherRequest request) {
        try {
            return ResponseEntity.ok(voucherService.updateVoucher(id, request));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
