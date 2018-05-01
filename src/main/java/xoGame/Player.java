package xoGame;

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

    public abstract Player getOppositePlayer();


}
