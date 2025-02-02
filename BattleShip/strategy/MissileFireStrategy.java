package BattleShip.strategy;

import BattleShip.entities.Cell;
import BattleShip.entities.Grid;
import BattleShip.entities.Player;

public interface MissileFireStrategy {
    public Cell hitcell(Grid grid,Player player);
}
