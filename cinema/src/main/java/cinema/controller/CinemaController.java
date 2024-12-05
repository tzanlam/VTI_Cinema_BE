package cinema.controller;

import cinema.modal.entity.Cinema;
import cinema.modal.request.CinemaRequest;
import cinema.modal.response.DTO.CinemaDTO;
import cinema.modal.response.DTO.MovieDTO;
import cinema.service.Cinema.CinemaService;
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

    @GetMapping("/find")
    public ResponseEntity<?> findCinemas() {
        try {
            List<Cinema> cinemas = cinemaService.findCinemas();
            List<CinemaDTO> cinemaDTOS = cinemas.stream()
                    .map(CinemaDTO:: new)
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
            return new ResponseEntity<>(new CinemaDTO(cinemaService.updateCinema(request, id)), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createCinema(@RequestBody CinemaRequest request) {
        try {
            return new ResponseEntity<>(new CinemaDTO(cinemaService.createCinema(request)), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            return new ResponseEntity<>(new CinemaDTO(cinemaService.changeStatus(id, status)), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            CinemaDTO cinemaDTO = new CinemaDTO(cinemaService.findById(id));
            return ResponseEntity.ok(cinemaDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findMovies/{id}")
    public ResponseEntity<?> findMovie(@PathVariable int id) {
        try{
            return ResponseEntity.ok(cinemaService.findMoviesByCinema(id).stream()
                    .map(MovieDTO::new)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }
    }
}
