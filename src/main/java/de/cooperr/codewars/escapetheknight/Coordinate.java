package de.cooperr.codewars.escapetheknight;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {

    private final List<Integer> knightAppearances = new ArrayList<>();

    private int x;
    private int y;

    private boolean calculated;

    public Coordinate(int x, int y, boolean calculated) {
        this.x = x;
        this.y = y;
        this.calculated = calculated;
    }

    public Coordinate(int x, int y) {
        this(x, y, false);
    }

    public static Coordinate fromString(String input) {
        return new Coordinate(input.charAt(0) - 97, input.charAt(1) - 49); // a = 97; 1 = 49
    }

    public String toString() {
        return new String(new char[]{(char) (x + 97), (char) (y + 49)});
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public List<Integer> getKnightAppearances() {
        return knightAppearances;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }
}
