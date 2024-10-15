import java.util.ArrayList;
import java.util.List;

public class EscapeTheKnight {

    public static String[] chooseKingMoves(String kingLocation, String knightLocation, int n) {
        var kingCoordinate = Coordinate.fromString(kingLocation);
        var knightCoordinate = Coordinate.fromString(knightLocation);
        var kingMoves = new ArrayList<String>();

        moveKing(kingCoordinate, knightCoordinate, n, kingMoves); // move the king n times so he never gets checked
        return kingMoves.toArray(String[]::new);
    }

    public static void moveKing(Coordinate kingCoordinate, Coordinate knightCoordinate, int n, List<String> kingMoves) {
        var deltaX = kingCoordinate.getX() - knightCoordinate.getX();
        var deltaY = kingCoordinate.getY() - knightCoordinate.getY();
        var fieldEven = (deltaX % 2 == 0) == (deltaY % 2 == 0);

        if (!fieldEven) {
            kingCoordinate.add((kingCoordinate.getX() > 4 ? -1 : 1), (kingCoordinate.getY() > 4 ? -1 : 1));
            kingMoves.add(kingCoordinate.toString());
        }
        for (int i = (!fieldEven ? 1 : 0); i < n; i++) {
            kingCoordinate.add((kingCoordinate.getX() > 4 ? -1 : 1), 0);
            kingMoves.add(kingCoordinate.toString());
        }
    }
}

class Coordinate {

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
