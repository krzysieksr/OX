package xoGame.xoGameExceptions;

public class CellBusyException extends Exception {
    int aCoor;
    int bCoor;

    public CellBusyException(int aCoor, int bCoor) {
        this.aCoor = aCoor;
        this.bCoor = bCoor;
    }

    public int getaCoor() {
        return aCoor;
    }

    public int getbCoor() {
        return bCoor;
    }

    @Override
    public String toString() {
        return "[" + aCoor + " " + bCoor + "]";
    }
}
