package shortCode.snakeLadder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BoardEntity> boardEntities = List.of(
                new Snake(17, 7), new Snake(54, 34),
                new Snake(62, 19), new Snake(98, 79),
                new Ladder(3, 38), new Ladder(24, 33),
                new Ladder(42, 93), new Ladder(72, 84)
        );        
        Game game=new Game(100, boardEntities);
        game.setPlayers(List.of("p1","p2"));
        game.play();
    }
}
