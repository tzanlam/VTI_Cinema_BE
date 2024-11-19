package cinema.repository;

import cinema.modal.entity.Ticket;
import cinema.modal.entity.constant.StatusTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findByIdAndStatus(int id, StatusTicket status);
}
