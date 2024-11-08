package cinema.controller;

import cinema.modal.entity.Cinema;
import cinema.modal.request.CinemaRequest;
import cinema.service.Cinema.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/find")
    public ResponseEntity<?> findCinemas() {
        try{
            List<Cinema> cinemas = cinemaService.findCinemas();
            return ResponseEntity.ok(cinemas);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateCinema(@PathVariable int id, @RequestBody CinemaRequest request) {
        try{
            return new ResponseEntity<>(cinemaService.updateCinema(request, id), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createCinema(@RequestBody CinemaRequest request) {
            return new ResponseEntity<>(cinemaService.createCinema(request), HttpStatus.CREATED);
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            return new ResponseEntity<>(cinemaService.changeStatus(id, status), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            return new ResponseEntity<>(cinemaService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
