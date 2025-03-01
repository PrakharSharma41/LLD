package com.example.battleship;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		// SpringApplication.run(BattleshipApplication.class, args);
		System.out.println("start");
        BattleShipGame game=new BattleShipGame(7);
        game.createPlayers(new ArrayList<>(Arrays.asList("player1","player2")));
        game.addShip("ship1", 1, 3, 3);
        // game.addShip("ship2", 1, 0, 4);
        // game.addShip("ship3", 1, 2, 4);
		System.out.println("finish");
        game.startGame();		
	}
}
