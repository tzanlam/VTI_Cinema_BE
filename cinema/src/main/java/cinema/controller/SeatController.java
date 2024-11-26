package cinema.controller;

import cinema.modal.entity.Seat;
import cinema.modal.response.DTO.SeatDTO;
import cinema.service.Seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/find")
    public ResponseEntity<?> findSeats() {
        try{
            List<Seat> seats = seatService.findSeats();
            List<SeatDTO> seatDTOS = seats.stream()
                    .map(SeatDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(seatDTOS);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findSeatById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(new SeatDTO(seatService.findById(id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
