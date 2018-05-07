package xoGame.components;

public enum Player {
    X {
        @Override
        public Player getOppositePlayer() {
            return O;
        }
    }, O {
        @Override
        public Player getOppositePlayer() {
            return X;
        }
    }, EMPTY {
        @Override
        public Player getOppositePlayer() {
            return EMPTY;
        }
    };

    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public abstract Player getOppositePlayer();


}
