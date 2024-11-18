package cinema.controller;

import cinema.modal.entity.Seat;
import cinema.modal.request.SeatRequest;
import cinema.modal.response.DTO.SeatDTO;
import cinema.service.Seat.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @Autowired
    private ModelMapper modelMapper;

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

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateSeat(@PathVariable int id, @RequestBody SeatRequest request) {
        try{
            Seat seat = seatService.updateSeat(id, request);
            SeatDTO seatDTO = new SeatDTO(seat);
            return new ResponseEntity<>(seatDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody SeatRequest request) {
        try {
            Seat seat = seatService.createSeat(request);
            SeatDTO seatDTO = new SeatDTO(seat);
            return new ResponseEntity<>(seatDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
