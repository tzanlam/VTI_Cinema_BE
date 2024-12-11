package cinema.modal.request;

import cinema.modal.entity.Contact;
import cinema.modal.entity.constant.City;
import cinema.modal.entity.constant.ServiceContact;
import lombok.Data;

@Data
public class ContactRequest {
    private String fullname;
    private String email;
    private String phoneNumber;
    private  int cinemaId;
    private String city;
    private String details;
    private String date;
    private String serviceContact;


    public Contact asContact(){
        Contact contact = new Contact();
        populateContactFields(contact);
        return contact;

    }
    public Contact updateContact(Contact contact) {
        populateContactFields(contact);
        return contact;
    }

    public void populateContactFields(Contact contact){
        contact.setFullName(fullname);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setDetails(details);
        try {
            contact.setCity(City.valueOf(city));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid city value: " + serviceContact);
        }
        try {
            contact.setServiceContact(ServiceContact.valueOf(serviceContact));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid service contact value: " + serviceContact);
        }


    }
}
