package cinema.controller;

import cinema.modal.request.PaymentRequest;
import cinema.service.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/find")
    public ResponseEntity<?> findAll(){
        try{
            return new ResponseEntity<>(paymentService.findPayments(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            return new ResponseEntity<>(paymentService.findPaymentById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest request){
        try{
            return ResponseEntity.ok(paymentService.createPayment(request));
        }catch (Exception e){
            return new ResponseEntity<>("Error"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePayment(@RequestBody PaymentRequest request, @PathVariable int id){
        try {
            return ResponseEntity.ok(paymentService.updatePayment(id, request));
        }catch (Exception e){
            return new ResponseEntity<>("Error"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
