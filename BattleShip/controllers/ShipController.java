package BattleShip.controllers;

import java.util.HashMap;

import BattleShip.entities.Ship;

public class ShipController {
    private HashMap<Ship,Boolean>isShipDestroyed;
    public Ship createShip(String name,int size){
        Ship ship=new Ship(name, size);
        isShipDestroyed.put(ship, false);
        return ship;
    }
    public boolean isDestroyed(Ship ship){
        return isShipDestroyed.get(ship);
    }
    public void setShipDestroyed(Ship ship){
        isShipDestroyed.put(ship, true);
    }
    public ShipController() {
        isShipDestroyed=new HashMap<>();
    }
}
