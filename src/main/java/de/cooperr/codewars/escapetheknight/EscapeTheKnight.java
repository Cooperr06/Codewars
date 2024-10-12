package de.cooperr.codewars.escapetheknight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EscapeTheKnight {

    private static final Vector2D[] KING_MOVEMENT = new Vector2D[]{new Vector2D(1, -1), new Vector2D(1, -1),
            new Vector2D(1, 0), new Vector2D(1, 1), new Vector2D(0, -1), new Vector2D(0, 1),
            new Vector2D(-1, -1), new Vector2D(-1, 0), new Vector2D(-1, 1)};
    private static final Vector2D[] KNIGHT_MOVEMENT = new Vector2D[]{new Vector2D(1, 2), new Vector2D(1, -2),
            new Vector2D(2, 1), new Vector2D(2, -1), new Vector2D(-1, 2), new Vector2D(-1, -2),
            new Vector2D(-2, 1), new Vector2D(-2, -1)};

    private static ChessBoard BOARD;

    public static void main(String[] args) {
        var knightStart = "h1";
        var kingStart = "a8";
        var moves = chooseKingMoves(kingStart, knightStart, 10);
        for (int i = 0; i < moves.length; i++) {
            var coordinate = BOARD.getBoard()[Coordinate.fromString(moves[i]).getX()][Coordinate.fromString(moves[i]).getY()];
            if (coordinate.getKnightAppearances().contains(i + 1)) {
                System.out.println("In round " + (i + 1) + " the king moved into a check at " + coordinate);
            }
        }
    }

    public static String[] chooseKingMoves(String kingLocation, String knightLocation, int n) {
        var board = new ChessBoard();
        var kingCoordinate = Coordinate.fromString(kingLocation);
        var knightCoordinate = Coordinate.fromString(knightLocation);
        var kingMoves = new ArrayList<String>();

        markBoard(board, knightCoordinate, 0, n); // calculate when the knight could be where
        for (int x = 0; x < board.getBoard().length; x++) {
            for (int y = 0; y < board.getBoard().length; y++) {
                System.out.println(board.getBoard()[x][y].toString() + ": " + Arrays.toString(board.getBoard()[x][y].getKnightAppearances().toArray()));
            }
        }
        for (int i = 1; i < n + 1; i++) {
            moveKing(board, kingCoordinate, i, kingMoves); // move the king n times so he never gets checked
        }
        BOARD = board;
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
                if (!possibleMove.getKnightAppearances().contains(currentRound)) {
                    possibleMove.getKnightAppearances().add(currentRound);
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
                    System.out.println("Round " + currentRound + ": " + kingCoordinate);
                    return;
                }
            }
        }
    }
}
