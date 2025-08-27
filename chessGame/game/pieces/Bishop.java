package game.pieces;

import game.Color;
import snakeGame.Board;
import snakeGame.Cell;

public class Bishop extends Piece{

    public Bishop(Color color){
        super(color);
    }
    @Override
    public boolean isValid(Board board, Cell from, Cell to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return (rowDiff == colDiff);
    }
    
}
