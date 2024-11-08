package cinema.controller;

import cinema.modal.request.SeatRoomRequest;
import cinema.service.SeatRoom.SeatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/seatRoom")
public class SeatRoomController {
    @Autowired
    private SeatRoomService seatRoomService;

    @GetMapping("/find")
    public ResponseEntity<?> findSeatRooms() {
        try{
            return ResponseEntity.ok(seatRoomService.findSeatRooms());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateSeatRoom(@PathVariable int id, @RequestBody SeatRoomRequest request) {
        try{
            return new ResponseEntity<>(seatRoomService.updateSeatRoom(id, request), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
// up lên git đi tui fix lại request
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createMovie(@RequestBody SeatRoomRequest request) {
        return new ResponseEntity<>(seatRoomService.createSeatRoom(request), HttpStatus.CREATED);
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            return new ResponseEntity<>(seatRoomService.changeStatus(id, status), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(seatRoomService.findById(id));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
