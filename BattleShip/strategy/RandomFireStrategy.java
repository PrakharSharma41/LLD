package BattleShip.strategy;

import java.util.HashMap;
import java.util.Map;

import BattleShip.entities.Cell;
import BattleShip.entities.Grid;
import BattleShip.entities.Player;

public class RandomFireStrategy implements MissileFireStrategy{
    Map<Cell,Boolean>hitMap;
    
    @Override
    public Cell hitcell(Grid grid,Player player) {
        int gridSize=grid.gridSize;
        int row=(int)(Math.random()*(gridSize-1));
        int col=(int)(Math.random()*(gridSize-1));
        Cell cell=grid.getCell(row, col);
        int tryCount=0;
        System.out.println("player here is "+player);
        while(hitMap.containsKey(cell)==true||player==cell.getPlayer()){
            row=(int)(Math.random()*(gridSize-1));
            col=(int)(Math.random()*(gridSize-1));            
            cell=grid.getCell(row, col);
            tryCount++;
            if(tryCount>20)break;
        }
        if(hitMap.containsKey(cell)==false&&player!=cell.getPlayer()){
            hitMap.put(cell, true);
        }
        System.out.println("row is "+row+" col is "+col);
        return cell;
    }

    public RandomFireStrategy() {
        hitMap=new HashMap<>();
    }
    
}
