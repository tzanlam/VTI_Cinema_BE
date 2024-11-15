package cinema.controller;

import cinema.modal.entity.Cinema;
import cinema.modal.request.CinemaRequest;
import cinema.modal.response.DTO.CinemaDTO;
import cinema.service.Cinema.CinemaService;
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
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/find")
    public ResponseEntity<?> findCinemas() {
        try {
            List<Cinema> cinemas = cinemaService.findCinemas();
            List<CinemaDTO> cinemaDTOS = cinemas.stream()
                    .map(cinema -> modelMapper.map(cinema, CinemaDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(cinemaDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateCinema(@PathVariable int id, @RequestBody CinemaRequest request) {
        try{
            return new ResponseEntity<>(modelMapper.map(cinemaService.updateCinema(request, id),CinemaDTO.class), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createCinema(@RequestBody CinemaRequest request) {
        try {
            return new ResponseEntity<>(modelMapper.map(cinemaService.createCinema(request), CinemaDTO.class), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            return new ResponseEntity<>(modelMapper.map(cinemaService.changeStatus(id, status),CinemaDTO.class), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            CinemaDTO cinemaDTO = modelMapper.map(cinemaService.findById(id), CinemaDTO.class);
            return ResponseEntity.ok(cinemaDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
