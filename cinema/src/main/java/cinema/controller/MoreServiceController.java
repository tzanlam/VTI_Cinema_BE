package cinema.controller;

import cinema.modal.entity.MoreService;
import cinema.modal.response.DTO.MoreServiceDTO;
import cinema.service.MoreService.MoreServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/moreService")
public class MoreServiceController {
    @Autowired
    private MoreServiceService moreServiceService;

    @GetMapping("/findServices")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findServices() {
        try{
            return ResponseEntity.ok(moreServiceService.findServices());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/createService")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createService(@RequestBody MoreServiceDTO dto) {
        try{
            return new ResponseEntity<>(moreServiceService.createService(dto), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("updateService/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> updateService(@PathVariable int id ,@RequestBody MoreServiceDTO dto) {
        try{
            return new ResponseEntity<>(moreServiceService.updateService(id, dto), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/changeStatus/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try{
            return new ResponseEntity<>(moreServiceService.changeStatus(id, status), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/serviceActive")
    public ResponseEntity<?> serviceActive() {
        try{
            return new ResponseEntity<>(moreServiceService.findStatusActive(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }
}
