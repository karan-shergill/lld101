package practice.modal;

public class Board {
    private static Board boardInstance;
    private Cell[][] board;
    private int height;
    private int width;

    private Board(int height, int width) {
        this.width = width;
        this.height = height;
        board = new Cell[height][width];
    }
    
    public synchronized static Board getBoardInstance(int height, int width) {
        if (boardInstance == null) {
            boardInstance = new Board(height, width);
            boardInstance.initializeCell();
        }
        return boardInstance;
    }

    private void initializeCell() {
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void printBoard() {
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                if (board[i][j].getFood() != null) {
                    // RENDER FOOD by food type
                } else if (board[i][j].isHasSnakeBody()) {
                    // RENDER SNAKE BODY
                } else if (board[i][j].isHasSnakeHead()) {
                    // RENDER SNAKE HEAD
                } else {
                    // RENDER GRASS
                }
            }
        }
    }

    public boolean isSnakeHeadCollidedToBorder(Cell snakeHead) {
        int row = snakeHead.getRow();
        int col = snakeHead.getCol();

        if (row<0 || row>=height || col<0 || col>=width) {
            return true;
        }
        return false;
    }
}
