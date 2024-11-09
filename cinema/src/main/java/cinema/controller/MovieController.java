package cinema.controller;

import cinema.modal.request.CinemaRequest;
import cinema.modal.request.LoginRequest;
import cinema.modal.request.MovieRequest;
import cinema.service.Account.AccountService;
import cinema.service.Cinema.CinemaService;
import cinema.service.Global.GlobalService;
import cinema.service.Movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> findMovies() {
        try{
            return ResponseEntity.ok(movieService.findMovies());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody MovieRequest request) {
        try{
            return new ResponseEntity<>(movieService.updateMovie(id, request), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.createMovie(request), HttpStatus.CREATED);
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            return new ResponseEntity<>(movieService.changeStatus(id, status), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(movieService.findById(id));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/findComingSoon")
    public ResponseEntity<?> findComingSoon() {
        try {
            return ResponseEntity.ok(movieService.findMovieComingSoon());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
