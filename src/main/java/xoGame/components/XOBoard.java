package xoGame.components;

import xoGame.coordinates.Cell;
import xoGame.coordinates.Coordinates;
import xoGame.xoGameExceptions.CellBusyException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class XOBoard {
    private final int x;
    private final int y;
    private Map<Integer, Player> board;
    private int recentTypedIndex;
    private final int boardRange;

    private XOBoard(int x, int y) {
        this.x = x;
        this.y = y;
        board = new HashMap<>();
        boardRange = x * y;
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

    public void applyMove(Cell cell, Player currentPlayer) throws CellBusyException {
        //TODO
        int inputIndex = cell.getCellIndex();
        if (inputIndex > boardRange) {
            throw new IllegalArgumentException();
        }
        if (board.containsKey(inputIndex)) {
            throw new CellBusyException(inputIndex);
        }
        recentTypedIndex = inputIndex;
        board.put(inputIndex, currentPlayer);
    }


    public void printTo(Consumer<String> output) {
        int displayCounter = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = stringBuilder.append("       ");
        for (int a = 1; a <= x; a++) {
            stringBuilder = appendBoardIndex(String.valueOf(a), stringBuilder);
        }
        output.accept(stringBuilder.toString());
        stringBuilder = new StringBuilder();

        for (int b = 1; b <= y; b++) {
            stringBuilder = appendBoardIndex(String.valueOf(b), stringBuilder);
            for (int a = 1; a <= x; a++) {
                int currCoord = getIndexAtMap(a, b);
                if (board.containsKey(currCoord)) {
                    stringBuilder = appendSignAtBoard(board.get(currCoord).name(), stringBuilder);
                } else {
                    stringBuilder = appendSignAtBoard(String.valueOf(displayCounter), stringBuilder);
                }
                displayCounter++;
            }
            output.accept(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
    }

    public int getIndexAtMap(int a, int b) {
        return (b - 1) * x + a;
    }

    public Coordinates getCoordinates(int index) {
        int xCoor = index % x;
        int yCoor = index / x + 1;
        return new Coordinates(xCoor, yCoor);
    }

    private StringBuilder appendBoardIndex(String boardIndex, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder();
        builder = builder.append("    ").append("   ");
        int offset = (7 - boardIndex.length()) / 2;
        builder.replace(offset, offset + boardIndex.length(), boardIndex);
        stringBuilder = stringBuilder.append(builder);
        return stringBuilder;
    }

    private StringBuilder appendSignAtBoard(String sign, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder();
        builder = builder.append("[   ").append("  ]");
        int offset = (7 - sign.length()) / 2;
        builder.replace(offset, offset + sign.length(), sign);
        stringBuilder = stringBuilder.append(builder);
        return stringBuilder;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRecentTypedIndex() {
        return recentTypedIndex;
    }

    public boolean isBoardFull() {
        int maxBoardSize = x * y;
        return board.size() == maxBoardSize;
    }

    public Player getCharAtIndex(int index) {
        return board.getOrDefault(index, Player.EMPTY);
    }

    public Player getCharAtCoor(Coordinates coordinates) {
        int indexAtMap = getIndexAtMap(coordinates.getX(), coordinates.getY());
        return getCharAtIndex(indexAtMap);
    }

    public int getShorterDimension() {
        return x >= y ? x : y;
    }
}
