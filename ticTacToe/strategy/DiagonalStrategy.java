package ticTacToe.strategy;

import ticTacToe.model.Board;
import ticTacToe.model.Player;

public class DiagonalStrategy implements WinStrategy{

    @Override
    public boolean checkWinner(Board board, Player player) {
        int row=board.getRow();
        int col=board.getCol();
        boolean isPlayerWinner=true;
        for(int i=0,j=0;i<row&&j<col;i++,j++){
            if(board.getCell(i, j).getPiece()!=player.getPiece()){
                isPlayerWinner=false;break;
            }
        }
        
        if(isPlayerWinner)return isPlayerWinner;

        for(int i=0,j=col-1;i<row&&j>=0;i++,j--){
            if(board.getCell(i, j).getPiece()!=player.getPiece()){
                isPlayerWinner=false;break;
            }
        }

        return isPlayerWinner;
    }
    
}
