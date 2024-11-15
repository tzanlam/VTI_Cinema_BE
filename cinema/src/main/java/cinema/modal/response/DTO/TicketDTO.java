package cinema.modal.response.DTO;

import cinema.modal.entity.Ticket;
import lombok.Data;

@Data
public class TicketDTO {
    private String ticket_id;
    private String seat_id;
    private String show_time_id;
    private String total_price;

    public TicketDTO(Ticket ticket) {
        this.ticket_id = String.valueOf(ticket.getId());
        this.seat_id = String.valueOf(ticket.getSeat());
        this.show_time_id = String.valueOf(ticket.getShowTime());
        this.total_price = String.valueOf(ticket.getTotalPrice());
    }
}
