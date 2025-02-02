package BattleShip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlayerController {
    List<Player>allPlayers;
    public PlayerController() {
        allPlayers=new LinkedList<>();
    }
    public void createPlayer(List<String>players){
        for(String name:players){
            Player player= new Player(name);
            allPlayers.add(player);    
        }
    }
    public List<Player> getPlayers() {
        return allPlayers;
    }
    public void setShipCount(int count,Player player){
        player.setShipCount(count);
    }
    // public void play(Player player,Cell cell)
}
