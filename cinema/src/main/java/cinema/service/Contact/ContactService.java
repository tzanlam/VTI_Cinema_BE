package cinema.service.Contact;

import cinema.modal.entity.Contact;
import cinema.modal.request.ContactRequest;

import java.util.List;

public interface ContactService {
    List<Contact> findContact();
    Contact findById(int id);
    Contact createContact(ContactRequest request);
    Contact updateContact(ContactRequest request, int id);
    Contact changeStatus(int id, String status) throws Exception;

}
