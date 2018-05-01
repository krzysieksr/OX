package xoGame.results;

import xoGame.Player;
import xoGame.results.MatchResult;

import static xoGame.results.Results.*;

public class ScoreBoard {
    private int xPoints;
    private int oPoints;

    public void addPointsForPlayer(MatchResult matchResult) {
        switch (matchResult) {
            case X:
                xPoints += 3;
            case O:
                oPoints += 3;
            case DRAW:
                xPoints += 1;
                oPoints += 1;
        }

    }

    public int getPlayerPoints(Player player) {
        return player.equals(Player.X) ? xPoints : oPoints;
    }

    public Results getCurrentGameResult() {
        return xPoints > oPoints ? X : xPoints < oPoints ? Y : DRAW;
    }
}
