package cinema.controller;

import cinema.modal.entity.Account;
import cinema.modal.request.AccountRequest;
import cinema.modal.request.LoginRequest;
import cinema.modal.response.DTO.AccountDTO;
import cinema.service.Account.AccountService;
import cinema.service.Global.GlobalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;



@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GlobalService globalService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try{
            return new ResponseEntity<>(globalService.login(request), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request){
        try{
            return new ResponseEntity<>(accountService.createAccount(request),HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> findAccounts () {
        try {
            List<Account> accounts = accountService.findAccounts();
            List<AccountDTO> accountDTOS = accounts.stream()
                    .map(account -> new AccountDTO(account))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(accountDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

            @PutMapping("/update/{id}")
            @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
            public ResponseEntity<?> updateAccount ( @PathVariable int id, @RequestBody AccountRequest request){
                try {
                    return new ResponseEntity<>(new AccountDTO(accountService.updateAccount(id, request)), HttpStatus.OK);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().build();
                }
            }

            @PostMapping("/changeStatus/{id}")
            @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER', 'USER')")
            public ResponseEntity<?> changeStatus ( @PathVariable int id, @RequestParam String status){
                try {
                    return new ResponseEntity<>(new AccountDTO(accountService.changeStatus(id, status)), HttpStatus.OK);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().build();
                }
            }

            @GetMapping("/findId/{id}")
            @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER','USER')")
            public ResponseEntity<?> findById ( @PathVariable int id){
                try {
                    return new ResponseEntity<>(new AccountDTO(accountService.findById(id)), HttpStatus.OK);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().build();
                }
            }
        }