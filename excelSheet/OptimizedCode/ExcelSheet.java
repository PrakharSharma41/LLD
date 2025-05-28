package OptimizedCode;

public class ExcelSheet {
    CellManager cellManager;
    ExcelSheet(){
        cellManager=new CellManager();
    }
    public void addCell(int row,int col,CellType cellType,CellFunction function){
        cellManager.addCell(row, col, cellType, function);
    }
    public Cell getCell(int row,int col){
        return cellManager.getCell(row, col);
    }
    public void setCellValue(int row,int col,Object object){
        cellManager.setCellValue(row,col,object);
    }
    public void setCellFunction(int row,int col,CellFunction cellFunction){
        cellManager.setCellFunction(row, col, cellFunction);
    }
    public void setCellObserver(int observeRow,int observeCol,int row,int col){
        cellManager.setCellObserver(observeRow, observeCol, row, col);
    }
}
