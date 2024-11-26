package cinema.controller;

import cinema.modal.entity.Room;
import cinema.modal.request.RoomRequest;
import cinema.modal.response.DTO.RoomDTO;
import cinema.service.Room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findRooms() {
        try{
            List<Room> rooms =  roomService.findRooms();
            List<RoomDTO> roomDTOS = rooms.stream()
                    .map(RoomDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(roomDTOS);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateRoom(@PathVariable int id, @RequestBody RoomRequest request) {
        try{
            Room room = roomService.updateRoom(id, request);
            RoomDTO roomDTO = new RoomDTO(room);
            return new ResponseEntity<>(roomDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest request) throws Exception {
        try {

            Room room = roomService.createRoom(request);
            RoomDTO roomDTO = new RoomDTO(room);
            return new ResponseEntity<>(roomDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            Room room = roomService.changeStatus(id, status);
            RoomDTO roomDTO = new RoomDTO(room);
            return new ResponseEntity<>(roomDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
