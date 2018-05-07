package xoGame.components;

import xoGame.results.MatchResult;
import xoGame.results.Results;

import static xoGame.results.Results.*;

public class ScoreBoard {
    private int xPoints;
    private int oPoints;

    public void addPointsForPlayer(MatchResult matchResult) {
        switch (matchResult) {
            case X:
                xPoints += 3;
                break;
            case O:
                oPoints += 3;
                break;
            case DRAW:
                xPoints += 1;
                oPoints += 1;
                break;
        }
    }

    public int getPlayerPoints(Player player) {
        return player.equals(Player.X) ? xPoints : oPoints;
    }

    public Results getCurrentGameResult() {
        return xPoints > oPoints ? X : xPoints < oPoints ? O : DRAW;
    }
}
