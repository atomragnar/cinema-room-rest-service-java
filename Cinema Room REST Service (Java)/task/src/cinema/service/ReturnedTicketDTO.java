package cinema.service;

import cinema.model.CinemaSeat;

public class ReturnedTicketDTO {

    CinemaSeat returned_ticket;

    public ReturnedTicketDTO(CinemaSeat returnedSeat) {
        returned_ticket = returnedSeat;
    }

    public CinemaSeat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(CinemaSeat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
