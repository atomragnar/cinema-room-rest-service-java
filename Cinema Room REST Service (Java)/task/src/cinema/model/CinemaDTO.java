package cinema.model;

import cinema.model.CinemaSeat;

import java.util.List;

public class CinemaDTO {

    int totalRows;
    int totalColumns;
    List<CinemaSeat> availableSeats;

    public CinemaDTO(int rows, int columns, List<CinemaSeat> availableSeats) {
        this.totalRows = rows;
        this.totalColumns = columns;
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalCols) {
        this.totalColumns = totalCols;
    }

    public List<CinemaSeat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<CinemaSeat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
