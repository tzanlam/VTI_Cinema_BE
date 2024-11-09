package cinema.service.Ticket;

import cinema.modal.entity.SeatRoom;
import cinema.modal.entity.ShowTime;
import cinema.modal.entity.Ticket;
import cinema.modal.request.TicketRequest;
import cinema.repository.SeatRoomRepository;
import cinema.repository.ShowTimeRepository;
import cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatRoomRepository seatRoomRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Override
    public Page<Ticket> findTickets(int page) {
        return ticketRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Ticket findById(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket createTicket(TicketRequest request) {
        Ticket ticket = new Ticket();
        ShowTime showTime = showTimeRepository.findById(request.getShowTime()).get();
        SeatRoom seatRoom = seatRoomRepository.findById(request.getSeatRoom()).get();
        ticket.setSeat(seatRoom);
        ticket.setShowTime(showTime);
        ticket.setTotalPrice(seatRoom.getSeat().getPrice());
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket updateTicket(int id, TicketRequest request) {
        Ticket ticket = findById(id);
        ShowTime showTime = showTimeRepository.findById(request.getShowTime()).get();
        SeatRoom seatRoom = seatRoomRepository.findById(request.getSeatRoom()).get();
        ticket.setSeat(seatRoom);
        ticket.setShowTime(showTime);
        ticket.setTotalPrice(seatRoom.getSeat().getPrice());
        ticketRepository.save(ticket);
        return ticket;
    }
}
