package game.pieces;

import game.Color;
import snakeGame.Board;
import snakeGame.Cell;

public class Rook extends Piece{

    public Rook(Color color){
        super(color);
    }
    @Override
    public boolean isValid(Board board, Cell from, Cell to) {
        return (from.getRow() == to.getRow() || from.getCol() == to.getCol());
    }
    
}
