package ticTacToe.strategy;

import ticTacToe.model.Board;
import ticTacToe.model.Player;

public class RowStrategy implements WinStrategy{

    @Override
    public boolean checkWinner(Board board, Player player) {
        int row=board.getRow();
        int col=board.getCol();
        boolean isPlayerWinner=true;
        for(int i=0;i<row;i++){
            isPlayerWinner=true;
            for(int j=0;j<col;j++){
                if(board.getCell(i, j).getPiece()!=player.getPiece()){
                    isPlayerWinner=false;break;
                }
            }
            if(isPlayerWinner==true)break;
        }
        return isPlayerWinner;
    }
    
}
