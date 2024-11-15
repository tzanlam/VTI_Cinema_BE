package cinema.controller;

import cinema.modal.entity.ShowTime;
import cinema.modal.request.ShowTimeRequest;
import cinema.modal.response.DTO.ShowTimeDTO;
import cinema.service.ShowTime.ShowTimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/showTime")
public class ShowTimeController {
    @Autowired
    private ShowTimeService showTimeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            ShowTime showTime = showTimeService.findByID(id);
            ShowTimeDTO dto = modelMapper.map(showTime, ShowTimeDTO.class);
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> create(@RequestBody ShowTimeRequest showTimeRequest){
        try{
            return new ResponseEntity<>(new ShowTimeDTO(showTimeService.createShowTime(showTimeRequest)), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ShowTimeRequest request){
        try{
            return ResponseEntity.ok(modelMapper.map(showTimeService.updateShowTime(id, request),ShowTimeDTO.class));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findMovie/{id}")
    public ResponseEntity<?> findMovie(@PathVariable int id){
        try {
            List<LocalTime> localTimes = showTimeService.findByMovie(id);
            List<String> timeDTO = localTimes.stream()
                    .map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm")))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(timeDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
