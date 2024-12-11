package cinema.modal.response.DTO;

import cinema.modal.entity.Contact;
import lombok.Data;

@Data
public class ContactDTO {
    private String contact_id;
    private String full_name;
    private String email;
    private String phone_number;
    private String city;
    private String details;
    private String service_contact;
    private String contact_status;
    private String cinema;

    public ContactDTO(Contact contact) {
        this.contact_id = String.valueOf(contact.getId());
        this.full_name = contact.getFullName();
        this.email = contact.getEmail();
        this.phone_number = contact.getPhoneNumber();
        this.city = String.valueOf(contact.getCity());
        this.details = contact.getDetails();
        this.service_contact = String.valueOf(contact.getServiceContact());
        this.contact_status = String.valueOf(contact.getContactStatus());
        this.cinema = String.valueOf(new CinemaDTO(contact.getCinema()));
    }
}
