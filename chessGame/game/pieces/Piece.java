package game.pieces;

import game.Board;
import game.Cell;
import game.Color;

public abstract class Piece{
    Color color;
    public Piece(Color color) {
        this.color = color;
    }
    public abstract boolean isValid(Board board,Cell start,Cell end);
    public Color getColor(){
        return color;
    }
}