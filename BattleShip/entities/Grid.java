package BattleShip.entities;

import java.util.List;

public class Grid {
    Cell[][]cell;
    public int gridSize;
    public Grid(int gridSize) {
        this.gridSize = gridSize;
        cell=new Cell[gridSize][gridSize];
    }
    public void initializeGridWithPlayers(List<Player>players){
        for(int i=0;i<gridSize;i++){
            for(int j=0;j<gridSize;j++){
                cell[i][j]=new Cell();
                if(j<gridSize/2){
                        cell[i][j].setPlayer(players.get(0));
                }else{
                    cell[i][j].setPlayer(players.get(1));
                }
            }
        }
    }
    public void setShipToLocation(Ship ship,int row,int column){
        int size=ship.getSize();
        for(int i=row;i<=row+size;i++){
            for(int j=column;j<=column+size;j++){
                cell[i][j].setShip(ship);
            }
        }
    }
    public Ship getShipAtLocation(int row,int column){
        return cell[row][column].getShip();
    }
    public Cell getCell(int row,int column){
        return cell[row][column];
    }
    public Player getPlayer(int row,int column){
        return cell[row][column].getPlayer();
    }
}
