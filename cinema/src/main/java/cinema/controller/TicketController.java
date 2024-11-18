package cinema.controller;

import cinema.modal.request.TicketRequest;
import cinema.service.Ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> findTicket() {
        try{
            return ResponseEntity.ok(ticketService.findTickets());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error"+e.getMessage());
        }
    }

    @GetMapping("/findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(ticketService.findById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error"+e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public ResponseEntity<?> create(@RequestBody TicketRequest request) {
        try{
            return ResponseEntity.ok(ticketService.createTicket(request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error"+e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','MANAGER')")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody TicketRequest request) {
        try{
            return ResponseEntity.ok(ticketService.updateTicket(id, request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error"+e.getMessage());
        }
    }
}
