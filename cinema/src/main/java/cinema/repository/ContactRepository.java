package cinema.repository;

import cinema.modal.entity.Contact;
import cinema.modal.entity.constant.ContactStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository< Contact, Integer> {
    List<Contact> findByContactStatus(ContactStatus contactStatus);
}
