package xoGame;

import java.util.function.Consumer;

public class XOBoard {
    public void applyMove(Coordinates coordinates, Player currentPlayer) {
        //TODO
    }

    public void printTo(Consumer<String>output){
        output.accept("[Board printing itself]");
    }
}
