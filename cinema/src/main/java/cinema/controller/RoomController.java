package cinema.controller;

import cinema.modal.request.RoomRequest;
import cinema.service.Room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/findRooms/{page}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findRooms(@PathVariable int page) {
        try{
            return ResponseEntity.ok(roomService.findRooms(page));
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/updateRoom/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateRoom(@PathVariable int id, @RequestBody RoomRequest request) {
        try{
            return new ResponseEntity<>(roomService.updateRoom(id, request), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/createRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest request) throws Exception {
        return new ResponseEntity<>(roomService.createRoom(request), HttpStatus.CREATED);
    }

    @GetMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestBody String status) {
        try {
            return new ResponseEntity<>(roomService.changeStatus(id, status), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}