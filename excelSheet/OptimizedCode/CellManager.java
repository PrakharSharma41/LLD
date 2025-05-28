package OptimizedCode;

import java.util.HashMap;
import java.util.Map;

public class CellManager {
    Map<String,Cell>cellPointer;
    CellObservable cellObservable;
    public CellManager() {
        cellPointer=new HashMap<>();
        cellObservable=new CellObservable();
    }
    public void addCell(int row,int col,CellType cellType,CellFunction function){
        Cell c=new Cell(row, col, cellType, null, function);// null value
        cellPointer.put(String.valueOf(row)+String.valueOf(col), c);
    }
    public Cell getCell(int row,int col){
        return cellPointer.get(String.valueOf(row)+String.valueOf(col));
    }
    public void setCellValue(int row,int col, Object object) {
        Cell cell=getCell(row, col);
        cell.updateValue(object);
        cellObservable.notifyObserver(cell);
    }
    public void setCellFunction(int row,int col,CellFunction cellFunction){
        Cell cell=getCell(row, col);
        cell.setCellFunction(cellFunction);
    }
    public void setCellObserver(int observeRow,int observeCol,int row,int col){
        Cell observableCell=getCell(observeRow, observeCol);
        Cell observerCell=getCell(row, col);

        cellObservable.addObserver(observableCell, observerCell);
        cellObservable.addObservable(observableCell,observerCell);
        cellObservable.notifyObserver(observableCell);
    }
    
}
