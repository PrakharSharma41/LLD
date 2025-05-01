package com.example.interview.entities;

public enum Direction {
    UP(1,0),
    DOWN(-1,0),
    LEFT(0,-1),
    RIGHT(0,1);
    int dx,dy;
    Direction(int dx,int dy){
        this.dx=dx;this.dy=dy;
    }
    public int[] move(int x,int y){
        return new int[]{x+dx,y+dy};
    }
    public static Direction fromString(String dir){
        return switch(dir){
            case "UP"-> UP;
            case "DOWN"-> DOWN;
            case "LEFT"-> LEFT;
            case "RIGHT"-> RIGHT;
            default -> null;
        };
    }
}
