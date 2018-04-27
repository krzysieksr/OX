package xoGame;

import xoGame.xoGameExceptions.CellBusyException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class XOBoard {
    private final int x;
    private final int y;
    private Map<Integer, Character> board;

    private XOBoard(int x, int y) {
        this.x = x;
        this.y = y;
        board = new HashMap<>();

    }

    public static XOBoard parse(String dimensionsAsString) {
        String[] parts = dimensionsAsString.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        if (x < 1 || y < 1) {
            throw new NegativeArraySizeException();
        }
        return new XOBoard(x, y);
    }

    public void applyMove(Coordinates coordinates, Player currentPlayer) throws CellBusyException {
        //TODO
        int aCoor = coordinates.getX();
        int bCoor = coordinates.getY();
        if (aCoor > x || bCoor > y) {
            throw new IllegalArgumentException();
        }
        int indexAtMap = getIndexAtMap(aCoor, bCoor);
        if (board.containsKey(indexAtMap)) {
            throw new CellBusyException(aCoor, bCoor);
        }
        board.put(indexAtMap, currentPlayer.name().charAt(0));
    }


    public void printTo(Consumer<String> output) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = stringBuilder.append("   ");
        for (int a = 1; a <= x; a++) {
            stringBuilder = appendBoardIndex((char) a, stringBuilder);
        }
        output.accept(stringBuilder.toString());
        stringBuilder = new StringBuilder();

        for (int b = 1; b <= y; b++) {
            stringBuilder = appendBoardIndex((char) b, stringBuilder);
            for (int a = 1; a <= x; a++) {
                int currCoord = getIndexAtMap(a, b);
                if (board.containsKey(currCoord)) {
                    stringBuilder = appendSignAtBoard(board.get(currCoord), stringBuilder);
                } else {
                    stringBuilder = appendSignAtBoard(' ', stringBuilder);
                }
            }
            output.accept(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
    }

    public int getIndexAtMap(int a, int b) {
        return (b - 1) * x + a;
    }

    private StringBuilder appendBoardIndex(int boardIndex, StringBuilder stringBuilder) {
        stringBuilder = stringBuilder.append(" ").append(boardIndex).append(" ");
        return stringBuilder;
    }

    private StringBuilder appendSignAtBoard(char sign, StringBuilder stringBuilder) {
        stringBuilder = stringBuilder.append("[").append(sign).append("]");
        return stringBuilder;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
