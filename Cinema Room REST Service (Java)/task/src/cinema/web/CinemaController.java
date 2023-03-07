
package cinema.web;



import cinema.exceptions.PurchaseRequestException;
import cinema.exceptions.WrongPasswordException;
import cinema.model.CinemaDTO;
import cinema.model.CinemaSeat;
import cinema.model.Constants;
import cinema.model.SeatDTO;
import cinema.service.CinemaService;
import cinema.service.ReturnedTicketDTO;
import cinema.service.StatisticsDTO;
import cinema.service.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CinemaController {

    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    @GetMapping("/seats")
    public ResponseEntity<CinemaDTO> getAvailableSeats() {
        return new ResponseEntity<>(cinemaService.getAvailableSeats(), HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<TicketDTO> handlePurchaseRequest(@RequestBody SeatDTO seat) {

        if (cinemaService.isSeatInvalid(seat)) {
            throw new PurchaseRequestException("The number of a row or a column is out of bounds!");
        }
        if (!cinemaService.isSeatEmpty(seat)) {
            throw new PurchaseRequestException("The ticket has been already purchased!");
        }

        // else book the seat
        TicketDTO bookedTicket = cinemaService.bookSeat(seat);
        return new ResponseEntity<>(bookedTicket, HttpStatus.OK);

    }

    @PostMapping("/return")
    public ResponseEntity<ReturnedTicketDTO> handleReturnRequest(@RequestBody TokenDTO token) {
        if (cinemaService.isTokenInvalid(token.getToken())) {
            throw new PurchaseRequestException("Wrong token!");
        }
        ReturnedTicketDTO refundTicket = cinemaService.refundTicket(token.getToken());
        return new ResponseEntity<>(refundTicket, HttpStatus.OK);
    }

    @PostMapping("/stats{password}")
    public ResponseEntity<StatisticsDTO> returnStatistics(@RequestParam(value = "password", required = false) @PathVariable String password) {
        if (password == null || !password.equals(Constants.PASSWORD)) {
            throw new WrongPasswordException("The password is wrong!");
        }
        return new ResponseEntity<>(cinemaService.getStatistics(), HttpStatus.OK);
    }


}
