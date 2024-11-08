package cinema.controller;

import cinema.modal.entity.Receipt;
import cinema.modal.request.ReceiptRequest;
import cinema.service.Receipt.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
@CrossOrigin("*")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/find/{page}")
    public ResponseEntity<?> findAll(@PathVariable int page){
        try{
            return ResponseEntity.ok(receiptService.findReceipts(page));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findId")
    public ResponseEntity<?> findById(@PathVariable int id){
        try {
            return ResponseEntity.ok(receiptService.findReceiptById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findIncome/{page}")
    public ResponseEntity<?> findIncome(@PathVariable int page){
        try{
            return ResponseEntity.ok(receiptService.findIncome(page));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findSpending/{page}")
    public ResponseEntity<?> findSpending(@PathVariable int page){
        try{
            return ResponseEntity.ok(receiptService.findSpending(page));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ReceiptRequest request){
        try{
            return ResponseEntity.ok(receiptService.ceateReceipt(request));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ReceiptRequest request){
        try{
            return ResponseEntity.ok(receiptService.updateReceipt(id, request));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/changeStatus/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestBody String status){
        try{
            return ResponseEntity.ok(receiptService.changeStatus(id, status));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
