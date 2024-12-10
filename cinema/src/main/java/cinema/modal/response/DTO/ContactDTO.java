package cinema.modal.response.DTO;

import cinema.modal.entity.Contact;

public class ContactDTO {
    private String contact_id;
    private String fullname;
    private String email;
    private String phone_number;
    private String region;
    private String details;
    private String contact_date;
    private String service_contact;
    private String contact_status;

    public ContactDTO(Contact contact) {
        this.contact_id = String.valueOf(contact.getId());
        this.fullname = contact.getFullName();
        this.email = contact.getEmail();
        this.phone_number = contact.getPhoneNumber();
        this.region = contact.getRegion();
        this.details = contact.getDetails();
        this.contact_date = String.valueOf(contact.getDate());
        this.service_contact = contact.getServiceContact() != null ? contact.getServiceContact().name() : null;
        this.contact_status = contact.getContactStatus() != null ? contact.getContactStatus().name() : null;
    }
}
