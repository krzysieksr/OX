package xoGame.results;

import xoGame.coordinates.Coordinates;
import xoGame.components.Player;
import xoGame.components.XOBoard;

public class CheckWinner {
    private int winCondition;
    private XOBoard xoBoard;

    public CheckWinner(int winCondition, XOBoard xoBoard) {
        this.winCondition = winCondition;
        this.xoBoard = xoBoard;
    }

    public boolean doWeHaveAWinner() {
        int recentTypedIndex = xoBoard.getRecentTypedIndex();
        Coordinates lastCoords = xoBoard.getCoordinates(recentTypedIndex);
        Player recentChar = xoBoard.getCharAtIndex(recentTypedIndex);

        return (checkWinner(lastCoords, recentChar, 1, 0)) ||
                checkWinner(lastCoords, recentChar, 0, 1) ||
                checkWinner(lastCoords, recentChar, 1, -1) ||
                checkWinner(lastCoords, recentChar, 1, 1);
    }


    private boolean checkWinner(Coordinates lastCoords, Player recentChar, int rowInc, int colInc) {
        int currentWinning = 1;

        int curRow = lastCoords.getY();
        int curCol = lastCoords.getX();
        while (true) {
            curRow += rowInc;
            curCol += colInc;
            Coordinates coords = new Coordinates(curRow, curCol);
            if (!areCoordsInRange(coords))
                break;

            if (xoBoard.getCharAtCoor(coords).equals(recentChar))
                currentWinning++;
            else
                break;
            if (currentWinning == winCondition)
                return true;
        }

        curRow = lastCoords.getY();
        curCol = lastCoords.getX();
        while (true) {
            curRow -= rowInc;
            curCol -= colInc;
            Coordinates coords = new Coordinates(curRow, curCol);
            if (!areCoordsInRange(coords))
                break;
            if (xoBoard.getCharAtCoor(new Coordinates(curRow, curCol)).equals(recentChar))
                currentWinning++;
            else
                break;
            if (currentWinning == winCondition)
                return true;
        }
        return false;
    }


    private boolean areCoordsInRange(Coordinates coords) {
        return coords.getY() >= 1 && coords.getY() <= getMaxRow() && coords.getX() >= 1 && coords.getX() <= getMaxCol();
    }

    private int getMaxCol() {
        return xoBoard.getX();
    }

    private int getMaxRow() {
        return xoBoard.getY();
    }
}
