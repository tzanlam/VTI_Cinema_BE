package cinema.controller;

import cinema.modal.entity.Contact;
import cinema.modal.request.ContactRequest;
import cinema.modal.response.DTO.ContactDTO;
import cinema.service.Contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> findContact() {
        try{
            List<Contact> contacts = contactService.findContact();
            List<ContactDTO> dtos = contacts.stream()
                    .map(ContactDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @GetMapping("/findId/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(new ContactDTO(contactService.findById(id)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> createContact(@RequestBody ContactRequest request) {
        try {
            return ResponseEntity.ok(new ContactDTO(contactService.createContact(request)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<?> updateContact(@PathVariable int id, @RequestBody ContactRequest request) {
        try{
            return ResponseEntity.ok(new ContactDTO(contactService.updateContact(request, id)));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/changeStatus/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestParam String status) {
        try {
            Contact contact = contactService.changeStatus(id, status);
            ContactDTO contactDTO = new ContactDTO(contact);
            return new ResponseEntity<>(contactDTO, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }
}
