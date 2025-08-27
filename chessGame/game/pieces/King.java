package game.pieces;

import game.Color;
import snakeGame.Board;
import snakeGame.Cell;

public class King extends Piece{
    public King(Color color){
        super(null);
    }
    @Override
    public boolean isValid(Board board, Cell from, Cell to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol()- from.getCol());
        return (rowDiff <= 1 && colDiff <= 1);
    }
    
}
