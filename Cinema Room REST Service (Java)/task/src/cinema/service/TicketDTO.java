package cinema.service;

import cinema.model.CinemaSeat;

import java.util.UUID;

public class TicketDTO {

    String token;
    CinemaSeat ticket;

    public TicketDTO() {

    }

    public TicketDTO(CinemaSeat ticket) {
        this.ticket = ticket;
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CinemaSeat getTicket() {
        return ticket;
    }

    public void setTicket(CinemaSeat ticket) {
        this.ticket = ticket;
    }
}
