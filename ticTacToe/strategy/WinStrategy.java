package ticTacToe.strategy;

import ticTacToe.model.Board;
import ticTacToe.model.Player;

public interface WinStrategy {
    boolean checkWinner(Board board,Player player);
}
