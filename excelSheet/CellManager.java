package excelSheet;

import java.util.HashMap;
import java.util.Map;

public class CellManager {
    class Pair{
        int row,col;
        Pair(int r,int c){
            this.row=r;this.col=c;
        }
        public boolean equals(Object obj){
            if(obj==null)return false;
            if(obj==this)return true;
            Pair p=(Pair)obj;
            if(p.row==this.row&&p.col==this.col)return true;
            return false;
        }
        public int hashCode(){
            return ((int)Math.random()*100);
        }
    }
    Map<Pair,Cell>cellPointer=new HashMap<>();
    
    public Cell createCell(int row,int col,ObjectType objectType,Object value){
        Cell c=new Cell(row, col, objectType, value);
        cellPointer.put(new Pair(row, col), c);
        return c;
    }
    public Cell getCell(int row,int col){
        return cellPointer.get(new Pair(row, col));
    }
}
