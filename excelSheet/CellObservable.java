package excelSheet;

public interface CellObservable {
    public void addObserver(CellObserver obs);
    public void removeObserver(CellObserver obs);
    public void notifyObserver();
}
