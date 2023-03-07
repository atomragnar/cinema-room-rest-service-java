package cinema.model;

public class SeatDTO {

    int row;
    int column;

    public SeatDTO() {

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row - 1;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column - 1;
    }
}
