package ticTacToe.model;

import ticTacToe.enums.Piece;

public class Board {
    Cell[][]cells;
    int row,col;
    public Board(int row,int col){
        this.row=row;this.col=col;
        cells=new Cell[row][col];
        initializeBoard();
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void initializeBoard(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                cells[i][j]=new Cell();
            }
        }
    }
    public void printBoard(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)System.out.print(cells[i][j].getPiece().getChar()+" ");
            System.out.println();
        }
    }
    public Cell getCell(int row,int col){
        return cells[row][col];
    }
    public void updateCell(int row,int col,Piece piece){
        Cell cell=cells[row][col];
        cell.setPiece(piece);
    }
    public boolean isCellEmpty(int row,int col){
        Cell cell=cells[row][col];
        return cell.isEmpty();
    }
    public boolean isBoardFull(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(cells[i][j].isEmpty())return false;
            }
        }
        return true;
    }
    public boolean makeMove(int r,int c,Piece piece){
        if(r>=0&&r<row&&c>=0&&c<col){
            Cell cell=getCell(r,c);
            if(cell.isEmpty()==false)return false;
            cell.setPiece(piece);
            return true;
        }
        return false;
    }
}
