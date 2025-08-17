package ticTacToe;

import java.util.List;

import ticTacToe.enums.Piece;
import ticTacToe.strategy.ColumnStrategy;
import ticTacToe.strategy.DiagonalStrategy;
import ticTacToe.strategy.RowStrategy;
import ticTacToe.strategy.WinStrategy;

public class Main {
    public static void main(String[] args) {
        List<WinStrategy>strategy=List.of(new RowStrategy(),new ColumnStrategy(),new DiagonalStrategy());
        Game game=new Game(3, 3, strategy);
        game.addPlayer(Piece.O, "p1");
        game.addPlayer(Piece.X, "p2");
        game.startGame();

    }
}
