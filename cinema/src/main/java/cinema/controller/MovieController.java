package cinema.controller;

import cinema.modal.entity.Movie;
import cinema.modal.request.MovieRequest;
import cinema.modal.response.DTO.MovieDTO;
import cinema.service.Movie.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> findMovies() {
        try{
            List<Movie> movies = movieService.findMovies();

            List<MovieDTO> movieDTOS = movies.stream()
                    .map(MovieDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(movieDTOS);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody MovieRequest request) {
        try{
            Movie movie = movieService.updateMovie(id, request);
            MovieDTO movieDTO = new MovieDTO(movie);
            return new ResponseEntity<>(movieDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest request) {
        try {
            Movie movie = movieService.createMovie(request);
            MovieDTO movieDTO = new MovieDTO(movie);
            return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            Movie movie = movieService.changeStatus(id, status);
            MovieDTO movieDTO = new MovieDTO(movie);
            return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            Movie movie = movieService.findById(id);
            MovieDTO movieDTO = new MovieDTO(movie);
            return ResponseEntity.ok(movieDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/findComingSoon")
    public ResponseEntity<?> findComingSoon() {
        try {
            List<MovieDTO> movieDTOS = movieService.findMovieComingSoon().stream()
                    .map(MovieDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(movieDTOS);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findSpecial")
    public ResponseEntity<?> findSpecial() {
        try{
            List<MovieDTO> movieDTOS = movieService.findMovieSpecial().stream()
                    .map(MovieDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(movieDTOS);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findShowing")
    public ResponseEntity<?> findShowing() {
        try{
            List<MovieDTO> movieDTOS = movieService.findMovieShowing().stream()
                    .map(MovieDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(movieDTOS);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
