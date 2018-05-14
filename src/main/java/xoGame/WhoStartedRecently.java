package xoGame;

import xoGame.components.Player;

public class WhoStartedRecently {
    String savedPlayer;

    public WhoStartedRecently(Player currentPlayer) {
        savedPlayer = currentPlayer.name();
    }

    public Player getWhoStartedRecently(Player player) {
        if (!player.name().equals(savedPlayer)) {
            player = player.getOppositePlayer();
        }
        savedPlayer = player.name();
        return player;
    }
}
