package cinema.modal.response.DTO;

import cinema.modal.entity.ShowTime;
import cinema.modal.entity.Ticket;
import lombok.Data;

@Data
public class TicketDTO {
    private String ticket_id;
    private String account;
    private String seat;
    private String show_time;
    private String ticket_price;
    private String ticket_status;

    public TicketDTO(Ticket ticket) {
        this.ticket_id = String.valueOf(ticket.getId());
        this.account = String.valueOf(new AccountDTO(ticket.getAccount()));
        this.seat = String.valueOf(new SeatDTO(ticket.getSeat()));
        this.show_time = String.valueOf(new ShowTimeDTO(ticket.getShowTime()));
        this.ticket_price = String.valueOf(ticket.getPrice());
        this.ticket_status = String.valueOf(ticket.getStatus());
    }
}
