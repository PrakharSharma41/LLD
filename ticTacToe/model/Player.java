package ticTacToe.model;

import ticTacToe.enums.Piece;

public class Player {
    @Override
    public String toString() {
        return "Player [name=" + name + "]";
    }
    Piece piece;
    public Player(Piece piece, String name) {
        this.piece = piece;
        this.name = name;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    String name;
    
}
