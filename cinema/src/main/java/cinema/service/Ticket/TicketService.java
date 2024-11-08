package cinema.service.Ticket;

import cinema.modal.entity.Ticket;
import cinema.modal.request.TicketRequest;
import org.springframework.data.domain.Page;

public interface TicketService {
    Page<Ticket> findTickets(int page);
    Ticket findById(int id);
    Ticket createTicket(TicketRequest request);
    Ticket updateTicket(int id, TicketRequest request);
}
