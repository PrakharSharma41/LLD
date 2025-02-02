package BattleShip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class BattleShipGame {
    Grid grid;
    PlayerController playerController;
    ShipController shipController;
    Player winnerPlayer;
    public BattleShipGame(int gridSize) {
        winnerPlayer=null;
        playerController=new PlayerController();
        shipController=new ShipController();
        grid=new Grid(gridSize);
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
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));        
        while(winnerPlayer==null){
            Player player=players.removeFirst();
            players.addLast(player);
            System.out.println("enter space separated row and column of where to fire the missile by "+player.getName());
            try{
                String input=br.readLine();
                String[] inputs=input.split(" ");
                int row=Integer.parseInt(inputs[0]);
                int column=Integer.parseInt(inputs[1]);
                play(grid.getPlayer(row, column),row,column);
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
    public boolean play(Player player,int row,int column){
        Ship ship=grid.getShipAtLocation(row, column);
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
