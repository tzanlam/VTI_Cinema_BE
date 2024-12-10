package cinema.service.Contact;

import cinema.modal.entity.Banner;
import cinema.modal.entity.Contact;
import cinema.modal.entity.Movie;
import cinema.modal.entity.constant.ContactStatus;
import cinema.modal.entity.constant.StatusMovie;
import cinema.modal.request.BannerRequest;
import cinema.modal.request.ContactRequest;
import cinema.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact findById(int id) {

        return contactRepository.findById(id).orElse(null);

    }

    @Override
    public List<Contact> findContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact createContact(ContactRequest request) {
        Contact contact = request.asContact();
        contactRepository.save(contact);
        return contact;
    }

    @Override
    public Contact updateContact(ContactRequest request, int id) {
        Contact contact = findById(id);
        if (contact != null){
            return contactRepository.save(request.updateContact(contact));

        }
        return null;
    }

    @Override
    public Contact changeStatus(int id, String newStatus) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            List<ContactStatus> validStatuses = Arrays.asList(ContactStatus.values());
            ContactStatus contactStatus = ContactStatus.valueOf(newStatus);
            if (validStatuses.contains(contactStatus)) {
                contact.setContactStatus(ContactStatus.valueOf(newStatus));
                contactRepository.save(contact);
                return contact;
            } else {
                throw new IllegalArgumentException("status not support");
            }
        } else {
            System.out.println("Movie not found with id: " + id);
        }
        return null;

    }


}
