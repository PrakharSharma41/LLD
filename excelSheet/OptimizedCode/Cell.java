package OptimizedCode;

import java.util.Objects;

public class Cell {
    int row,col;
    CellType cellType;
    Object value;
    CellFunction cellFunction;
    public Cell(int row, int col, CellType cellType, Object value, CellFunction cellFunction) {
        this.row = row;
        this.col = col;
        this.cellType = cellType;
        this.value = value;
        this.cellFunction = cellFunction;
    }
    @Override
    public String toString() {
        return "Cell [row=" + row + ", col=" + col + ", cellType=" + cellType + ", value=" + value + ", cellFunction="
                + cellFunction + "]";
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public CellType getCellType() {
        return cellType;
    }
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
    public Object getValue() {
        return value;
    }
    public void updateValue(Object value) {
        this.value = value;
    }
    public CellFunction getCellFunction() {
        return cellFunction;
    }
    public void setCellFunction(CellFunction cellFunction) {
        this.cellFunction = cellFunction;
    }
    @Override
    public int hashCode(){
        return Objects.hash(row,col);
    }
    @Override
    public boolean equals(Object o){
        if(o==null)return false;
        if(o==this)return true;
        Cell c=(Cell)o;
        if(c.row==this.row&&c.col==this.col)return true;
        return false;
    }
    
}
