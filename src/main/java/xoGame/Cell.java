package xoGame;

public class Cell {
    private final int cellIndex;

    private Cell(int cellIndex) {
        this.cellIndex = cellIndex;
    }


    public int getCellIndex() {
        return cellIndex;
    }

    public static Cell parse(String cellIndexAsString) {
        int cellIndex = Integer.parseInt(cellIndexAsString);
        if (cellIndex < 1) {
            throw new IllegalArgumentException();
        }
        return new Cell(cellIndex);
    }
}
