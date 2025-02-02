package BattleShip;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BattleShipGame game=new BattleShipGame(7);
        game.createPlayers(new ArrayList<>(Arrays.asList("player1","player2")));
        game.addShip("ship1", 3, 0, 0);
        game.addShip("ship2", 1, 0, 4);
        game.addShip("ship3", 1, 2, 4);
        game.startGame();
    }
}
