package game.pieces;

import game.Board;
import game.Cell;
import game.Color;

public class Rook extends Piece{

    public Rook(Color color){
        super(color);
    }
    @Override
    public boolean isValid(Board board, Cell from, Cell to) {
        return (from.getRow() == to.getRow() || from.getCol() == to.getCol());
    }
    
}
