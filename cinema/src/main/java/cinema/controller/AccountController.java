package cinema.controller;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;
import cinema.modal.response.DTO.AccountDTO;
import cinema.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;



@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request){
        try{
            return new ResponseEntity<>(accountService.createAccount(request),HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> findAccounts () {
        try {
            List<Account> accounts = accountService.findAccounts();
            List<AccountDTO> accountDTOS = accounts.stream()
                    .map(AccountDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(accountDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("findEmail/{email}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'USER')")
    public ResponseEntity<?> findAccountsByEmail(@PathVariable String email) {
        try{
            return ResponseEntity.ok(new AccountDTO(accountService.findByEmail(email)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

            @PutMapping("/update/{id}")
            @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
            public ResponseEntity<?> updateAccount ( @PathVariable int id, @RequestBody AccountRequest request){
                try {
                    return new ResponseEntity<>(new AccountDTO(accountService.updateAccount(id, request)), HttpStatus.OK);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("Error: "+e.getMessage());
                }
            }

            @PostMapping("/changeStatus/{id}")
            @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'USER')")
            public ResponseEntity<?> changeStatus ( @PathVariable int id, @RequestParam String status){
                try {
                    return new ResponseEntity<>(new AccountDTO(accountService.changeStatus(id, status)), HttpStatus.OK);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("Error: "+e.getMessage());
                }
            }

        @GetMapping("/findId/{id}")
        @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER','USER')")
        public ResponseEntity<?> findById ( @PathVariable int id){
            try {
                return new ResponseEntity<>(new AccountDTO(accountService.findById(id)), HttpStatus.OK);
            } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/checkCode")
    public ResponseEntity<?> checkCoded(@RequestParam String email, @RequestParam String code) {
        try {
            Account account = accountService.confirmAccount(email, code);
            return ResponseEntity.ok(new AccountDTO(account));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }
}