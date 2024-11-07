package cinema.controller;

import cinema.modal.request.RoomRequest;
import cinema.modal.request.SeatRequest;
import cinema.service.Room.RoomService;
import cinema.service.Seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/findSeats")
    public ResponseEntity<?> findSeats() {
        try{
            return ResponseEntity.ok(seatService.findSeats());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/updateSeat/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateSeat(@PathVariable int id, @RequestBody SeatRequest request) {
        try{
            return new ResponseEntity<>(seatService.updateSeat(id, request), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/createRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody SeatRequest request) {
        return new ResponseEntity<>(seatService.createSeat(request), HttpStatus.CREATED);
    }

    @GetMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestBody String status) {
        try {
            return new ResponseEntity<>(seatService.changeStatus(id, status), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
}
