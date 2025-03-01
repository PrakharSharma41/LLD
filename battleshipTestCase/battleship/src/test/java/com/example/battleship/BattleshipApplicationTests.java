package com.example.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.battleship.entities.Cell;
import com.example.battleship.entities.Player;
import com.example.battleship.entities.Ship;

@SpringBootTest
class BattleshipApplicationTests {

	@Test
	void contextLoads() {
	}
    private BattleShipGame game;

    @BeforeEach
    public void setUp() {
        game = new BattleShipGame(7);
        game.createPlayers(new ArrayList<>(Arrays.asList("player1", "player2")));
    }

    @Test
    public void testCreatePlayers() {
        List<Player> players = game.playerController.getPlayers();
        assertEquals(2, players.size());
        assertEquals("player1", players.get(0).getName());
        assertEquals("player2", players.get(1).getName());
    }

    @Test
    public void testAddShip() {
        game.addShip("ship1", 3, 0, 0);
        Ship ship = game.grid.getShipAtLocation(0, 0);
        assertNotNull(ship);
        assertEquals("ship1", ship.getName());
    }

    @Test
    public void testShipPlacement() {
        game.addShip("ship1", 2, 2, 2);
        Ship ship = game.grid.getShipAtLocation(2, 2);
        assertNotNull(ship);
        assertEquals(2, ship.getSize());
    }

    @Test
    public void testPlayMiss() {
        Cell cell = game.grid.getCell(5, 5);
        boolean result = game.play(game.playerController.getPlayers().get(0), cell);
        assertFalse(result, "The attack should miss if no ship is present");
    }

    @Test
    public void testPlayHit() {
        game.addShip("ship1", 1, 3, 3);
        Cell cell = game.grid.getCell(3, 3);
        boolean result = game.play(game.playerController.getPlayers().get(0), cell);
        assertTrue(result, "The attack should hit the ship");
    }

    @Test
    public void testWinnerDetection() {
        game.addShip("ship1", 1, 3, 3);
        Cell cell = game.grid.getCell(2, 4);
		assertEquals(false,game.play(game.playerController.getPlayers().get(0), cell));
        game.isWinnerFound();
		assertEquals("player2", game.winnerPlayer.getName());

        // assertNotNull(game.winnerPlayer);
    }
}