package cinema.controller;

import cinema.modal.entity.Movie;
import cinema.modal.entity.Room;
import cinema.modal.request.RoomRequest;
import cinema.modal.response.DTO.RoomDTO;
import cinema.service.Room.RoomService;
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
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/find/{page}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findRooms(@PathVariable int page) {
        try{
            List<Room> rooms = (List<Room>) roomService.findRooms(page);
            List<RoomDTO> roomDTOS = rooms.stream()
                    .map(room -> modelMapper.map(room, RoomDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(roomDTOS);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateRoom(@PathVariable int id, @RequestBody RoomRequest request) {
        try{
            Room room = roomService.updateRoom(id, request);
            RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
            return new ResponseEntity<>(roomService.updateRoom(id, request), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest request) throws Exception {
        Room room = roomService.createRoom(request);
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        return new ResponseEntity<>(roomService.createRoom(request), HttpStatus.CREATED);

    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            Room room = roomService.changeStatus(id, status);
            RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
            return new ResponseEntity<>(roomService.changeStatus(id, status), HttpStatus.OK);
        }catch (Exception e){

            return ResponseEntity.noContent().build();
        }
    }
}
