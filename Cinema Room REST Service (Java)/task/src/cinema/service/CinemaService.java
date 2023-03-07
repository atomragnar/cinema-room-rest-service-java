package cinema.service;


import cinema.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CinemaService {

    private Cinema cinemaRepository;
    private static Map<String, CinemaSeat> tokensToSeats = new ConcurrentHashMap<>();
    private static int income = 0;

    @Autowired
    public CinemaService(Cinema cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public CinemaDTO getAvailableSeats() {
        return cinemaRepository.getCurrentSeatsAvailable();
    }

    public int getAmountEmptySeats() {
        return cinemaRepository.getCurrentSeatsAvailable().getAvailableSeats().size();
    }


    public TicketDTO bookSeat(SeatDTO seat) {
        CinemaSeat bookedSeat = cinemaRepository.bookSeat(seat.getRow(), seat.getColumn());
        income += bookedSeat.getPrice();
        TicketDTO ticket = new TicketDTO(bookedSeat);
        tokensToSeats.put(ticket.getToken(), bookedSeat);
        return ticket;
    }

    public boolean isTokenInvalid(String token) {
        return !tokensToSeats.containsKey(token);
    }

    public ReturnedTicketDTO refundTicket(String token) {
        CinemaSeat returnedSeat = tokensToSeats.get(token);
        tokensToSeats.remove(token, returnedSeat);
        income -= returnedSeat.getPrice();
        cinemaRepository.setSeatAvailable(returnedSeat.getRow(), returnedSeat.getColumn());
        return new ReturnedTicketDTO(returnedSeat);
    }

    public boolean isSeatEmpty(SeatDTO seat) {
        return cinemaRepository.isSeatEmpty(seat.getRow(), seat.getColumn());
    }

    public boolean isSeatInvalid(SeatDTO seat) {
        return !(seat.getRow() < Constants.ROWS && seat.getRow() >= 0
                && seat.getColumn() < Constants.COLUMNS && seat.getColumn() >= 0);

    }

    public StatisticsDTO getStatistics() {
        int emptySeats = getAmountEmptySeats();
        int soldSeats = Constants.TOTAL_SEATS - emptySeats;
        return  new StatisticsDTO(income, emptySeats, soldSeats);
    }


}
