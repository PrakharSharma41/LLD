package game;

import snakeGame.pieces.Piece;

public class Cell {
    Piece piece;
    int row,col;
    Cell(int row,int col){
        this.row=row;this.col=col;
    }
    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }

    public int getRow() { return row; }
    public int getCol() { return col; }    

}
