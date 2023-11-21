public class Seat {
    private int row;
    private int column;    
    private SeatCategory category;
    private int price;
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public Seat(int row, int column, SeatCategory category, int price) {
        this.row = row;
        this.column = column;
        this.category = category;
        this.price = price;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public SeatCategory getCategory() {
        return category;
    }
    public void setCategory(SeatCategory category) {
        this.category = category;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}

