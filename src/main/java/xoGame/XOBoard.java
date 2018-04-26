package xoGame;

import java.util.function.Consumer;

public class XOBoard {
    private final int x;
    private final int y;
    char[][] board;

    private XOBoard(int x, int y) {
        this.x = x;
        this.y = y;
        this.board = new char[x][y];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static XOBoard parse(String dimensionsAsString) throws IllegalArgumentException {
        String[] parts = dimensionsAsString.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        return new XOBoard(x, y);
    }

    public void applyMove(Coordinates coordinates, Player currentPlayer) {
        //TODO
    }


    public void printTo(Consumer<String> output) {
        output.accept("[Board printing itself]");
        for (int i = 0; i < x; i++) {
            String outputString = "";
            for (int j = 0; j < y; j++) {
                outputString = outputString + "[" + board[i][j] + "]";
            }
            output.accept(outputString);
        }
    }
}
