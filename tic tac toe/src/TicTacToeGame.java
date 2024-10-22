import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import Model.PieceType;
import Model.Board;
import Model.Player;
import Model.PlayingPieceO;
import Model.PlayingPieceX;
import javafx.util.Pair;
public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;
    public void initializeGame(){
        players=new LinkedList<Player>();
        PlayingPieceX piece1=new PlayingPieceX();
        Player player1=new Player(piece1,"Player 1");

        PlayingPieceO piece2=new PlayingPieceO();
        Player player2=new Player(piece2,"Player 2");
        players.add(player1);
        players.add(player2);
        gameBoard=new Board(3);
    }
    public String startGame(){
        boolean noWinner=true;
        while(noWinner){
            Player playerTurn=players.removeFirst();
            gameBoard.printBoard();
            List<Pair<Integer, Integer>> freeSpaces =  gameBoard.getFreeCells();
            if(freeSpaces.size()==0){
                noWinner=false;continue;
            }
            System.out.print("Player:" + playerTurn.name + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s =inputScanner.nextLine();
            String[] values = s.split(",");
            int r=Integer.parseInt(values[0]),c=Integer.parseInt(values[1]);
            boolean pieceAdded=gameBoard.addPiece(r, c, playerTurn.PlayingPiece);
            if(!pieceAdded){
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            boolean winner = isThereWinner(r, c, playerTurn.PlayingPiece.pieceType);
            if(winner) {
                return playerTurn.name;
            }

            players.add(playerTurn);            
        }
        return "tie";
    }
    public boolean isThereWinner(int r, int c, PieceType pieceType){
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;
        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[r][i]==null || gameBoard.board[r][i].pieceType!=pieceType){
                rowMatch = false;
            }
        }
        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][c] == null || gameBoard.board[i][c].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
