package xoGame.xoGameExceptions;

public class CellBusyException extends Exception {
    int cellIndex;

    public CellBusyException(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    @Override
    public String toString() {
        return "[" + cellIndex + "]";
    }
}
