package xoGame;

import java.util.function.Consumer;

public class XOBoard {
    private final int x;
    private final int y;

    private XOBoard(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static XOBoard parse(String dimensionsAsString) {
//        String[] parts = dimensionsAsString.split(" ");
//        int x = Integer.parseInt(parts[0]);
//        int y = Integer.parseInt(parts[1]);
//        return new XOBoard(x, y);
        return new XOBoard(5,3);
    }

    public void applyMove(Coordinates coordinates, Player currentPlayer) {
        //TODO
    }



    public void printTo(Consumer<String>output){
        output.accept("[Board printing itself]");
    }
}
