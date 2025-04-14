package com.example.game;




import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class SnakeGameTest {

    @Test
    public void testInitialMoveNoFood() {
        SnakeGame game = new SnakeGame(5, 5, List.of());
        assertEquals(0, game.move("R")); // Valid move, no food
        assertEquals(-1, game.move("D")); // Valid move, no food
    }

    @Test
    public void testEatingFoodIncreasesScore() {
        SnakeGame game = new SnakeGame(5, 5, List.of(new int[]{0, 1}, new int[]{0, 2}));
        assertEquals(1, game.move("R")); // Eats food at (0,1)
        assertEquals(2, game.move("R")); // Eats food at (0,2)
    }

    @Test
    public void testHittingWallReturnsMinusOne() {
        SnakeGame game = new SnakeGame(3, 3, List.of());
        game.move("R"); // (0,1)
        game.move("R"); // (0,2)
        assertEquals(-1, game.move("R")); // Hits wall at (0,3)
    }

    @Test
    public void testSelfCollisionReturnsMinusOne() {
        SnakeGame game = new SnakeGame(4, 4, List.of(
            new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 2},new int[]{1,1}
        ));
        assertEquals(1, game.move("R")); // (0,1)
        assertEquals(2, game.move("R")); // (0,2)
        assertEquals(3, game.move("U")); // (1,2)
        assertEquals(4, game.move("L")); // (1,1)
        assertEquals(-1, game.move("D")); // (0,1)
    }

    @Test
    public void testNoFoodStillMovesFine() {
        SnakeGame game = new SnakeGame(2, 2, List.of());
        assertEquals(0, game.move("R"));
        assertEquals(-1, game.move("D"));
    }
}
