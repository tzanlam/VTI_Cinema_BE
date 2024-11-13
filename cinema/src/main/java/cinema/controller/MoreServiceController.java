package cinema.controller;

import cinema.modal.entity.MoreService;
import cinema.modal.request.MoreServiceRequest;
import cinema.modal.response.DTO.MoreServiceDTO;
import cinema.service.MoreService.MoreServiceService;
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
@RequestMapping("/moreService")
public class MoreServiceController {
    @Autowired
    private MoreServiceService moreServiceService;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findServices() {
        try{
            List<MoreService> moreServices = moreServiceService.findServices();
            List<MoreServiceDTO> moreServiceDTOS = moreServices.stream()
                    .map(moreService -> modelMapper.map(moreService, MoreServiceDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(moreServiceDTOS);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findServiceById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(modelMapper.map(moreServiceService.findById(id), MoreServiceDTO.class));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createService(@RequestBody MoreServiceRequest request) {
        try{
            return new ResponseEntity<>(modelMapper.map(moreServiceService.createService(request),MoreServiceDTO.class), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> updateService(@PathVariable int id ,@RequestBody MoreServiceRequest request) {
        try{
            return new ResponseEntity<>(modelMapper.map(moreServiceService.updateService(id, request),MoreServiceDTO.class), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try{
            return new ResponseEntity<>(modelMapper.map(moreServiceService.changeStatus(id, status),MoreServiceDTO.class), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findActive")
    public ResponseEntity<?> serviceActive() {
        try{
            return new ResponseEntity<>(modelMapper.map(moreServiceService.findStatusActive(),MoreServiceDTO.class), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
    }
}
