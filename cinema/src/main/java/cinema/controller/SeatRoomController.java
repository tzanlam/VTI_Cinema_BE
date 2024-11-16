package cinema.controller;

import cinema.modal.entity.SeatRoom;
import cinema.modal.request.SeatRoomRequest;
import cinema.modal.response.DTO.SeatDTO;
import cinema.modal.response.DTO.SeatRoomDTO;
import cinema.service.SeatRoom.SeatRoomService;
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
@RequestMapping("/seatRoom")
public class SeatRoomController {
    @Autowired
    private SeatRoomService seatRoomService;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/find")
    public ResponseEntity<?> findSeatRooms() {
        try{
            List<SeatRoom> seatRooms = seatRoomService.findSeatRooms();
            List<SeatDTO> seatDTOS = seatRooms.stream()
                    .map(seatRoom -> modelMapper.map(seatRoom, SeatDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(seatDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateSeatRoom(@PathVariable int id, @RequestBody SeatRoomRequest request) {
        try{
            SeatRoom seatRoom = seatRoomService.updateSeatRoom(id, request);
            SeatRoomDTO seatRoomDTO = modelMapper.map(seatRoom, SeatRoomDTO.class);
            return new ResponseEntity<>(seatRoomDTO, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
// up lên git đi tui fix lại request
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createMovie(@RequestBody SeatRoomRequest request) {
        SeatRoom seatRoom = seatRoomService.createSeatRoom(request);
        SeatRoomDTO seatRoomDTO = modelMapper.map(seatRoom, SeatRoomDTO.class);
        return new ResponseEntity<>(seatRoomDTO, HttpStatus.CREATED);
    }

//    @PostMapping("/changeStatus/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
//        try {
//            return new ResponseEntity<>(seatRoomService.changeStatus(id, status), HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.noContent().build();
//        }
//    }

    @GetMapping("findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            SeatRoom seatRoom = seatRoomService.findById(id);
            SeatRoomDTO seatRoomDTO = modelMapper.map(seatRoom, SeatRoomDTO.class);
            return ResponseEntity.ok(seatRoomDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
