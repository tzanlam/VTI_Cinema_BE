package cinema.modal.response.DTO;

import cinema.modal.entity.Ticket;
import lombok.Data;

@Data
public class TicketDTO {
    private String ticket_id;
    private String account_name;
    private String seat_room_name;
    private String start_time;
    private String show_date;
    private String room;
    private String total_price;

    public TicketDTO(Ticket ticket) {
        this.ticket_id = String.valueOf(ticket.getId());
        this.account_name = ticket.getAccount().getFullName();
        this.seat_room_name = ticket.getSeat().getRoom().getName();
        this.start_time = String.valueOf(ticket.getShowTime().getStartTime());
        this.show_date = String.valueOf(ticket.getShowTime().getShowDate());
        this.room = ticket.getSeat().getRoom().getName();
        this.total_price = String.valueOf(ticket.getPrice());
    }
}
