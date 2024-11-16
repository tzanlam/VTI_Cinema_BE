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
                    .map(seat -> modelMapper.map(seat, SeatDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(seatDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateSeat(@PathVariable int id, @RequestBody SeatRequest request) {
        try{
            Seat seat = seatService.updateSeat(id, request);
            SeatDTO seatDTO = modelMapper.map(seat,SeatDTO.class);
            return new ResponseEntity<>(seatDTO, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody SeatRequest request) {
        try {
            Seat seat = seatService.createSeat(request);
            SeatDTO seatDTO = modelMapper.map(seat,SeatDTO.class);
            return new ResponseEntity<>(seatDTO, HttpStatus.CREATED);
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
