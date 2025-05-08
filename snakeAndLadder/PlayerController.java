import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import entities.Player;

public class PlayerController {
    Deque<Player>playersList;
    int numberOfPlayers;
    PlayerController(int count){
        this.numberOfPlayers=count;
        playersList=new LinkedList<>();
    }
    public void addPlayers(int numberOfPlayers, int[] playersPositions) {
        if(playersPositions!=null){
            for(int i=0;i<playersPositions.length;i++){
                Player player=new Player(String.valueOf(i), playersPositions[i]);
                playersList.addLast(player);
            }
        }
    }
}
