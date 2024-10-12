package de.cooperr.codewars.escapetheknight;

public class ChessBoard {

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
