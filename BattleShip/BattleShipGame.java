package BattleShip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import BattleShip.controllers.PlayerController;
import BattleShip.controllers.ShipController;
import BattleShip.entities.Cell;
import BattleShip.entities.Grid;
import BattleShip.entities.Player;
import BattleShip.entities.Ship;
import BattleShip.strategy.MissileFireStrategy;
import BattleShip.strategy.RandomFireStrategy;

public class BattleShipGame {
    Grid grid;
    PlayerController playerController;
    ShipController shipController;
    Player winnerPlayer;
    MissileFireStrategy missileFireStrategy;
    public BattleShipGame(int gridSize) {
        winnerPlayer=null;
        playerController=new PlayerController();
        shipController=new ShipController();
        grid=new Grid(gridSize);
        missileFireStrategy=new RandomFireStrategy();
    }
    public void createPlayers(List<String>players){
        playerController.createPlayer(players);
        grid.initializeGridWithPlayers(playerController.getPlayers());
    }
    public void addShip(String name,int size,int row,int column){
        Ship ship=shipController.createShip(name, size);
        grid.setShipToLocation(ship, row, column);
        Cell cell=grid.getCell(row, column);
        Player player=cell.getPlayer();
        playerController.setShipCount(player.getShipCount()+1, player);
    }
    public void startGame(){
        List<Player>players=playerController.getPlayers();
        while(winnerPlayer==null){
            Player player=players.removeFirst();
            System.out.println("player "+player+" turn");
            players.addLast(player);
            try{
                Cell cell=missileFireStrategy.hitcell(grid, player);
                play(cell.getPlayer(),cell);
                isWinnerFound();
                if(winnerPlayer!=null){
                    System.out.println("Player :"+winnerPlayer+" won the game");
                }
            }catch(Exception e){
                System.out.println("error in input, play again "+e);
                players.addFirst(player);
            }
        }
    }
    public boolean play(Player player,Cell cell){
        Ship ship=cell.getShip();
        System.out.println("ship at this location is "+ship);
        if(ship==null){
            System.out.println("ship missed by player: "+player.getName());
            return false;
        }
        System.out.println("shipController is "+shipController);
        if(shipController.isDestroyed(ship)==false){
            shipController.setShipDestroyed(ship);
            playerController.setShipCount(player.getShipCount()-1, player); 
            shipController.setShipDestroyed(ship);
            return true;
        }else{
            System.out.println("ship missed by player: "+player.getName());
            return false;
        }
    }
    public void isWinnerFound(){
        List<Player>players=playerController.getPlayers();
        int size=players.size();
        int nonZeroCount=0;
        for(int i=0;i<size;i++){
            Player player=players.removeFirst();
            if(player.getShipCount()>0){
                winnerPlayer=player;
                nonZeroCount++;
            }
            players.addLast(player);
        }
        if(nonZeroCount==players.size())winnerPlayer=null;
    }
}
