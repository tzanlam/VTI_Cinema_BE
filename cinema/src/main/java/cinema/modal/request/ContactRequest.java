package cinema.modal.request;

import cinema.modal.entity.Contact;
import cinema.modal.entity.constant.ContactStatus;
import cinema.modal.entity.constant.ServiceContact;
import cinema.modal.entity.constant.StatusMovie;
import lombok.Data;

import static cinema.util.CheckEqualsEnum.checkEqualsEnum;
import static cinema.util.ConvertDateTime.convertToLocalDate;
import static cinema.util.ConvertDateTime.convertToLocalTime;

@Data
public class ContactRequest {
    private String fullname;
    private String email;
    private String phoneNumber;
    private  int cinemaId;
    private String region;
    private String details;
    private String date;
    private String serviceContact;
    private String contactStatus;

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
        contact.setRegion(region);
        contact.setDetails(details);
        contact.setDate(convertToLocalDate(date));
        try {
            contact.setServiceContact(ServiceContact.valueOf(serviceContact));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid service contact value: " + serviceContact);
        }
        if (checkEqualsEnum(ContactStatus.class, contactStatus)) {
            contact.setContactStatus(ContactStatus.valueOf(contactStatus));
        }else {
            throw new IllegalArgumentException("Invalid language");
        }
        contact.setContactStatus(ContactStatus.PENDING);
    }
}
