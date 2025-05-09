package DesignPatterns.prototype;

public class Shape implements Prototype {
    private int x;
    private int y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Shape clone() {
        return new Shape(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Shape at (" + x + "," + y + ")";
    }
}