package com.example.battleship.strategy;

import com.example.battleship.entities.Cell;
import com.example.battleship.entities.Grid;
import com.example.battleship.entities.Player;

public interface MissileFireStrategy {
    public Cell hitcell(Grid grid,Player player);
}
