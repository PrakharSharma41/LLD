package ticTacToe.model;

import ticTacToe.enums.Piece;

public class Cell {
    Piece piece;
    Cell(){
        piece=Piece.EMPTY;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public boolean isEmpty(){
        return piece==Piece.EMPTY;
    }
}
