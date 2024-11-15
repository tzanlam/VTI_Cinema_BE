package cinema.controller;

import cinema.modal.entity.Seat;
import cinema.modal.request.SeatRequest;
import cinema.modal.response.DTO.SeatDTO;
import cinema.service.Seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
//
//    @GetMapping("/find")
//    public ResponseEntity<?> findSeats() {
//        try{
//            List<Seat> seats = seatService.findSeats();
//            List<SeatDTO> seatDTOS = seats.stream()
//                    .map(seat -> modelMapper)
//
//            return ResponseEntity.ok(seatService.findSeats());
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.noContent().build();
//        }
//    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateSeat(@PathVariable int id, @RequestBody SeatRequest request) {
        try{
            return new ResponseEntity<>(seatService.updateSeat(id, request), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody SeatRequest request) {
        try {
            return new ResponseEntity<>(seatService.createSeat(request), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/changeType/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String type) {
        try {
            return new ResponseEntity<>(seatService.changeType(id, type), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
}
