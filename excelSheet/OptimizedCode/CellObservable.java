package OptimizedCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CellObservable {
    Map<Cell,List<Cell>>observableCells;// Cell is being observed by List<Cell>
    Map<Cell,List<Cell>>observerCells;// Cell is observing List<Cell>cells
    OperationStrategy operationStrategy;
    public CellObservable() {
        observableCells=new HashMap<>();
        observerCells=new HashMap<>();
    }
    public void addObserver(Cell observable,Cell observer){
        observableCells.compute(observable, (key, list) -> {
            if (list == null)list = new ArrayList<>();
            list.add(observer);
            return list;
        });        
    }
    public void addObservable(Cell observable,Cell observer){
        observerCells.compute(observer, (key, list) -> {
            if (list == null)list = new ArrayList<>();
            list.add(observable);
            return list;
        });        
        System.out.println();
    }
    public void notifyObserver(Cell observableCell){
        List<Cell>observers=observableCells.get(observableCell);
        if(observers==null)return;
        for(Cell c :observers){
            List<Cell>observables=observerCells.get(c);
            if(observables==null)continue;
            operationStrategy=Utility.getStrategy(c.getCellFunction());
            int result=operationStrategy.perform(observables.toArray(new Cell[0]));
            c.updateValue(result);
        }
    }
    
}
