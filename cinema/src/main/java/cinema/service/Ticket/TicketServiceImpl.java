package cinema.service.Ticket;

import cinema.modal.entity.Account;
import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.ShowTime;
import cinema.modal.entity.Ticket;
import cinema.modal.entity.constant.StatusSeatRoom;
import cinema.modal.entity.constant.StatusTicket;
import cinema.modal.request.TicketRequest;
import cinema.repository.AccountRepository;
import cinema.repository.SeatRoomRepository;
import cinema.repository.ShowTimeRepository;
import cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatRoomRepository seatRoomRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Ticket> findTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket createTicket(TicketRequest request) {
        Ticket ticket = new Ticket();
        populateTicket(ticket, request);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket updateTicket(int id, TicketRequest request) {
        Ticket ticket = findById(id);
        populateTicket(ticket, request);
        ticketRepository.save(ticket);
        return ticket;
    }

    private Ticket populateTicket(Ticket ticket, TicketRequest request) {
        ShowTime showTime = showTimeRepository.findById(request.getShowTime()).get();
        SeatRoom seatRoom = seatRoomRepository.findById(request.getSeatRoom()).get();
        Account account = accountRepository.findById(request.getAccount()).get();
        ticket.setAccount(account);
        ticket.setSeat(seatRoom);
        ticket.setShowTime(showTime);
        ticket.setStatus(StatusTicket.UNPAID);
        seatRoom.setStatus(StatusSeatRoom.BOOKED);
        return ticket;
    }
}
