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
    public Game(int sz,int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions,int numberOfPlayer,int[][]playerPosition){
        initializeGame(sz, numberOfSnakes,snakesPositions,  numberOfLadders,laddersPositions,numberOfPlayer,playerPosition);
    }
    private void initializeGame(int sz,int numberOfSnakes,int[][]snakesPositions, int numberOfLadders,int[][]laddersPositions,int numberOfPlayer,int[][]playersPosition){
        board=new Board(sz,numberOfLadders,snakesPositions,numberOfLadders,laddersPositions);
        dice=new Dice(1);
        // provide dice rolling strategy here
        playerController.addPlayers(numberOfPlayer,playersPosition);
    }
    public void startGame() {
        Deque<Player>playersList=playerController.playersList;
        while(winner==null){
            Player player=playersList.pop();
            playersList.addLast(player);
            int diceNumber=dice.rollDice(); // use diceStrategy here
            int playerNewPosition = player.currentPosition + diceNumber;
            playerNewPosition = jumpCheck(playerNewPosition);
            player.currentPosition=playerNewPosition;
            if(playerNewPosition >= board.cells.length * board.cells.length-1){
                winner = player;
            }

        }
    }
    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition>board.cells.length*board.cells.length-1){
            return playerNewPosition;
        }
        Cell cell = board.getCell(playerNewPosition);
        if(cell!=null){
            playerNewPosition=cell.jump.end;
        }
        return playerNewPosition;
    }
}
