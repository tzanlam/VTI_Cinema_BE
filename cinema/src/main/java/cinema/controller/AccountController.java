package cinema.controller;

import cinema.modal.request.AccountRequest;
import cinema.modal.request.LoginRequest;
import cinema.service.Account.AccountService;
import cinema.service.Global.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private GlobalService globalService;
    @PostMapping("/login")
    // path http:localhost:8081/account/login
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try{
            return new ResponseEntity<>(globalService.login(request), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("createAccount")
    // path http:localhost:8081/account/createAccount
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request){
        try{
            return new ResponseEntity<>(accountService.createAccount(request),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAccounts/{page}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    // path http:localhost:8081/account/findAccounts/{page}
    public ResponseEntity<?> findAccounts(@PathVariable  int page){
        try{
            return new ResponseEntity<>(accountService.findAccounts(page),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAccount/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    // path http:localhost:8081/account/updateAccount/{id}
    public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody AccountRequest request){
        try{
            return new ResponseEntity<>(accountService.updateAccount(id,request),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/changeStatus/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    // path http:localhost:8081/account/changeStatus/{id}?status=status
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status){
        try{
            return new ResponseEntity<>(accountService.changeStatus(id,status),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER','USER')")
    // http://localhost:8081/account/findById/{id}
    public ResponseEntity<?> findById(@PathVariable int id){
        try{
            return new ResponseEntity<>(accountService.findById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
