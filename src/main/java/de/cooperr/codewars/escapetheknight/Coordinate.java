package de.cooperr.codewars.escapetheknight;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate fromString(String input) {
        return new Coordinate(input.charAt(0) - 97, input.charAt(1) - 49); // a = 97; 1 = 49
    }

    @Override
    public String toString() {
        return new String(new char[]{(char) (x + 97), (char) (y + 49)});
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
