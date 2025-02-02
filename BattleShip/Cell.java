package BattleShip;

public class Cell {
    Ship ship; // contains ship or not
    Player player; // belongs to which player
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
