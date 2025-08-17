package ticTacToe.strategy;

import ticTacToe.model.Board;
import ticTacToe.model.Player;

public class ColumnStrategy implements WinStrategy{

    @Override
    public boolean checkWinner(Board board, Player player) {
        int row=board.getRow();
        int col=board.getCol();
        boolean isPlayerWinner=true;
        for(int i=0;i<col;i++){
            isPlayerWinner=true;
            for(int j=0;j<row;j++){
                if(board.getCell(j, i).getPiece()!=player.getPiece()){
                    isPlayerWinner=false;break;
                }
            }
            if(isPlayerWinner==true)break;
        }
        return isPlayerWinner;
    }
    
}
