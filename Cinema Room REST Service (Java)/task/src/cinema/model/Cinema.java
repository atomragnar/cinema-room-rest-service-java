package cinema.model;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cinema.model.Constants.COLUMNS;
import static cinema.model.Constants.ROWS;

@Repository
public class Cinema {


    private static int[][] cinemaSeats = new int[ROWS][COLUMNS];
   /* private static Map<String, Integer> cinemaSeats = new ConcurrentHashMap<>();*/

    public Cinema() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                cinemaSeats[i][j] = Constants.EMPTY_SEAT;
                /*StringBuilder sb = new StringBuilder();
                sb.append(i).append(j);
                cinemaSeats.put(sb.toString(), Constants.EMPTY_SEAT);*/
            }
        }
    }

    List<CinemaSeat> getAvailableSeats() {
        List<CinemaSeat> seats = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(cinemaSeats[i][j] == Constants.EMPTY_SEAT) {
                    seats.add(new CinemaSeat(i + 1, j + 1));
                }
            }
        }
        return seats;
    }

    public CinemaDTO getCurrentSeatsAvailable() {
        return new CinemaDTO(ROWS, COLUMNS, getAvailableSeats());
    }

    private String seatKey(int row, int col) {
        StringBuilder sb = new StringBuilder();
        return sb.append(row).append(col).toString();
    }

    public boolean isSeatEmpty(int row, int col) {
        return cinemaSeats[row][col] == Constants.EMPTY_SEAT;
    }

    public void setSeatAvailable(int row, int col) {
        cinemaSeats[--row][--col] = Constants.EMPTY_SEAT;
    }


    public CinemaSeat bookSeat(int row, int col) {
        cinemaSeats[row][col] = Constants.BOOKED_SEAT;
        return new CinemaSeat(++row, ++col);
    }


}
