package snakeandfood.utils;

public class Board {
    private int height;
    private int width;
    private static Board boardInstance;

    private Board(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public static Board getBoardInstance(int height, int width) {
        if (boardInstance == null) {
            boardInstance = new Board(height, width);
        }
        return boardInstance;
    }

    public static void resetInstance() {
        boardInstance = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
