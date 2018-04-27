package xoGame;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public static Coordinates parse(String coordinatesAsString) {
        String[] parts = coordinatesAsString.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
        return new Coordinates(x, y);
    }
}
