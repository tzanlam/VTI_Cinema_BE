package cinema.service.Ticket;

import cinema.modal.entity.Ticket;
import cinema.modal.request.TicketRequest;

import java.util.List;

public interface TicketService {
    List<Ticket> findTickets();
    Ticket findById(int id);
    Ticket createTicket(TicketRequest request);
    Ticket updateTicket(int id, TicketRequest request);
}
