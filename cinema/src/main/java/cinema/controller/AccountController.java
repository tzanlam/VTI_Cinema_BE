package cinema.controller;

import cinema.modal.request.LoginRequest;
import cinema.service.Account.AccountService;
import cinema.service.Global.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private GlobalService globalService;
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try{
            return new ResponseEntity<>(globalService.login(request), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
