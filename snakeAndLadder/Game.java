import java.util.Deque;

import entities.Board;
import entities.Cell;
import entities.Dice;
import entities.Player;

public class Game {
    Board board;
    Dice dice;
    Player winner;
    PlayerController playerController;
    public Game(int sz,int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions,int numberOfPlayer,int[]playerPosition){
        this.playerController=new PlayerController(numberOfPlayer);
        initializeGame(sz, numberOfSnakes,snakesPositions,  numberOfLadders,laddersPositions,numberOfPlayer,playerPosition);
    }
    private void initializeGame(int sz,int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions,int numberOfPlayer,int[]playersPosition){
        board=new Board(sz,numberOfSnakes,snakesPositions,numberOfLadders,laddersPositions);
        dice=new Dice(1);
        // provide dice rolling strategy here
        playerController.addPlayers(numberOfPlayer,playersPosition);
    }
    public void startGame() {
        Deque<Player>playersList=playerController.playersList;
        int boardSize=board.getBoardSize();
        while(winner==null){
            Player player=playersList.pop();
            playersList.addLast(player);
            int diceNumber=dice.rollDice(); // use diceStrategy here
            int playerNewPosition = player.currentPosition + diceNumber;
            System.out.println("player "+player+" rolled dice "+diceNumber);
            System.out.println("new position is "+playerNewPosition);
            playerNewPosition = jumpCheck(playerNewPosition);
            System.out.println("new position after jump check is "+playerNewPosition);
            player.currentPosition=playerNewPosition;
            if(playerNewPosition >= boardSize-1){
                winner = player;
                System.out.println("winner is "+winner);
            }
        }
    }
    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition>=board.getBoardSize()-1){
            return playerNewPosition;
        }
        Cell cell = board.getCell(playerNewPosition);
        if(cell!=null&&cell.jump!=null){
            playerNewPosition=cell.jump.end;
        }
        return playerNewPosition;
    }
}
