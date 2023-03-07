package cinema.model;

import java.util.Objects;

public class CinemaSeat {
    int  row;
    int column;
    int price;

    public CinemaSeat(int row, int column) {
        this.row = row;
        this.column = column;
        if (row <= Constants.ROW_PRICE_CUTOFF) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaSeat that = (CinemaSeat) o;
        return row == that.row && column == that.column && price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price);
    }
}
