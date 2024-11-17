package cinema.controller;

import cinema.modal.request.LoginRequest;
import cinema.service.Global.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin
public class GlobalController {
    @Autowired
    private GlobalService globalService;

    @PostMapping("/login/email")
    public ResponseEntity<?> loginByEmail(@RequestBody LoginRequest request){
        try {
            return ResponseEntity.ok(globalService.loginByEmail(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
