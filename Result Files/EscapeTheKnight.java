import java.util.ArrayList;
import java.util.List;

public class EscapeTheKnight {

    private static final Vector2D[] KING_MOVEMENT = new Vector2D[]{new Vector2D(1, -1), new Vector2D(1, -1),
            new Vector2D(1, 0), new Vector2D(1, 1), new Vector2D(0, -1), new Vector2D(0, 1),
            new Vector2D(-1, -1), new Vector2D(-1, 0), new Vector2D(-1, 1)};
    private static final Vector2D[] KNIGHT_MOVEMENT = new Vector2D[]{new Vector2D(1, 2), new Vector2D(1, -2),
            new Vector2D(2, 1), new Vector2D(2, -1), new Vector2D(-1, 2), new Vector2D(-1, -2),
            new Vector2D(-2, 1), new Vector2D(-2, -1)};

    public static String[] chooseKingMoves(String kingLocation, String knightLocation, int n) {
        var board = new ChessBoard();
        var kingCoordinate = Coordinate.fromString(kingLocation);
        var knightCoordinate = Coordinate.fromString(knightLocation);
        var kingMoves = new ArrayList<String>();

        markBoard(board, knightCoordinate, 1, n); // calculate when the knight could be where
        for (int i = 1; i < n + 1; i++) {
            moveKing(board, kingCoordinate, i, kingMoves); // move the king n times so he never gets checked
        }
        return kingMoves.toArray(String[]::new);
    }

    public static void markBoard(ChessBoard board, Coordinate knightCoordinate, int currentRound, int maxRound) {
        var possibleMoves = new ArrayList<Coordinate>();
        for (var vector2D : KNIGHT_MOVEMENT) {
            var possibleX = knightCoordinate.getX() + vector2D.x();
            var possibleY = knightCoordinate.getY() + vector2D.y();
            // check if the possible move would be outside the board boundaries
            if (board.getBoard().length > possibleX && possibleX >= 0 &&
                    board.getBoard()[possibleX].length > possibleY && possibleY >= 0) {
                var possibleMove = board.getBoard()[possibleX][possibleY];
                if (!possibleMove.getKnightAppearances().contains(currentRound - 1)) {
                    possibleMove.getKnightAppearances().add(currentRound - 1);
                }
                possibleMoves.add(possibleMove);
            }
        }
        if (currentRound < maxRound) {
            possibleMoves.forEach(possibleMove -> markBoard(board, possibleMove, currentRound + 1, maxRound));
        }
    }

    public static void moveKing(ChessBoard board, Coordinate kingCoordinate, int currentRound, List<String> kingMoves) {
        for (var vector2D : KING_MOVEMENT) {
            var possibleX = kingCoordinate.getX() + vector2D.x();
            var possibleY = kingCoordinate.getY() + vector2D.y();
            if (board.getBoard().length > possibleX && possibleX >= 0 &&
                    board.getBoard()[possibleX].length > possibleY && possibleY >= 0) {
                if (!board.getBoard()[possibleX][possibleY].getKnightAppearances().contains(currentRound)) {
                    kingCoordinate.add(vector2D.x(), vector2D.y());
                    kingMoves.add(kingCoordinate.toString());
                    return;
                }
            }
        }
    }
}


class ChessBoard {

    private final Coordinate[][] board = new Coordinate[8][8];

    public ChessBoard() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = new Coordinate(x, y);
            }
        }
    }

    public Coordinate[][] getBoard() {
        return board;
    }
}

class Coordinate {

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

record Vector2D(int x, int y) {
}
