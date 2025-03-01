package com.example.battleship.entities;

public class Player {
    String name;
    int shipCount=0;
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getShipCount() {
        return shipCount;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + "]";
    }
}
