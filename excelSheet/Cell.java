package excelSheet;

import java.util.ArrayList;
import java.util.List;

public class Cell implements CellObserver,CellObservable{
    int row,col;
    ObjectType objectType;
    Object value;
    OperationStrategy operation;
    List<CellObservable> observableCells=new ArrayList<>();
    List<CellObserver>observerCells=new ArrayList<>();
    OperationType operationType;
    public Cell(int row, int col, ObjectType objectType, Object value) {
        this.row = row;
        this.col = col;
        this.objectType = objectType;
        this.value = value;
    }   
    public void updateCell(Object value){
        this.value=value;
        notifyObserver();
    }
    public void doOperation(OperationType operationType,Cell... cells){
        if(operationType==OperationType.SUM){           
            for(Cell c:cells){
                // also need to add that this observer is already added or not to cell c
                c.addObserver(this);
                this.observableCells.add(c);
            } 
            this.operationType=operationType;
            this.operation=new SumOperationImpl();
            this.operation.perform(cells);
        }
    }
    @Override
    public void update() {
        this.doOperation(operationType, (Cell[])observableCells.toArray());
    }
    @Override
    public void addObserver(CellObserver obs) {
        observerCells.add(obs);
    }
    @Override
    public void removeObserver(CellObserver obs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeObserver'");
    }
    @Override
    public void notifyObserver() {
        for(CellObserver obj: observerCells){
            obj.update();
        }
    }
}
