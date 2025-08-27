package game.pieces;

import game.Color;
import snakeGame.Board;
import snakeGame.Cell;

public class Queen extends Piece{

    public Queen(Color color){
        super(color);
    }
    @Override
    public boolean isValid(Board board, Cell from, Cell to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return (rowDiff == colDiff) || (from.getRow() == to.getRow() || from.getCol() == to.getCol());
    }
    
}
