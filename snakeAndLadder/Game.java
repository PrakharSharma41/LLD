import java.util.Deque;

public class Game {
    Board board;
    Dice dice;
    Deque<Player>playersList;
    Player winner;
    public Game(){
        initializeGame();
    }
    private void initializeGame(){
        Board board=new Board(10,5,4);
        dice=new Dice(1);
        addPlayers();
    }
    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playersList.add(player1);
        playersList.add(player2);
    }
    public void startGame() {
        while(winner==null){
            Player player=playersList.pop();
            playersList.addLast(player);
            int diceNumber=dice.rollDice();
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
        Cells cell = board.getCell(playerNewPosition);
        if(cell!=null){
            playerNewPosition=cell.jump.end;
        }
        return playerNewPosition;
    }
}
