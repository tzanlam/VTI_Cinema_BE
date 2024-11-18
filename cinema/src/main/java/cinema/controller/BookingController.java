package cinema.controller;

import cinema.modal.entity.Booking;
import cinema.modal.request.BookingRequest;
import cinema.modal.response.DTO.BookingDTO;
import cinema.service.Booking.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findAll() {
        try{
            List<Booking> bookings = bookingService.findBookings();
            List<BookingDTO> dtos = bookings.stream()
                    .map(BookingDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: "+e.getMessage());
        }
    }

    @GetMapping("findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(new BookingDTO(bookingService.findById(id)), HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try{
            return new ResponseEntity<>(new BookingDTO(bookingService.createBooking(request)), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }


    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> updateBooking(@PathVariable int id, @RequestBody BookingRequest request) {
        try{
            return new ResponseEntity<>(new BookingDTO(bookingService.updateBooking(id, request)), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(400).body("Error: "+e.getMessage());
        }
    }

    @PostMapping("changeStatus/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try{
            return new ResponseEntity<>(new BookingDTO(bookingService.changeStatus(id, status)), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
