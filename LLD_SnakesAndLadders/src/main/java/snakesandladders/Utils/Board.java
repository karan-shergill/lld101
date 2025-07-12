package snakesandladders.Utils;

import snakesandladders.obstacleFactory.ObstacleType;

public class Board {
    private Cell[][] board;
    private int row;
    private int col;

    public Board(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Board dimensions must be positive");
        }
        this.row = row;
        this.col = col;

        board = new Cell[row][col];
        initializeBoard();
    }

    private void initializeBoard() {
        int value = this.row * this.col;
        boolean leftToRight = true;

        for (int i=0; i<this.row; i++) {
            if (leftToRight) {
                for (int j=0; j<this.col; j++) {
                    board[i][j] = new Cell(i, j, value);
                    value--;
                }
            } else {
                for (int j=this.col-1; j>=0; j--) {
                    board[i][j] = new Cell(i, j, value);
                    value--;
                }
            }
            leftToRight = !leftToRight;
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell move(Cell currPosition, int diceCount) {
        int currValue = currPosition.getPosition();
        int newPosition = currValue + diceCount;
        
        if (newPosition > this.row * this.col) {
            return new Cell(-1, -1, newPosition);
        }
        
        int[] rowAndCol = CommonUtils.getRowAndColViaPosition(newPosition, this.row, this.col);
        
        return board[rowAndCol[0]][rowAndCol[1]];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void print() {
        System.out.println("\n" + "=".repeat(col * 10 + 1));
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (board[i][j].isHasObstacle()) {
                    if (board[i][j].getObstacle().getObstacleType() == ObstacleType.SNAKE) {
                        System.out.printf("%8s ", "🐍:" + board[i][j].getObstacle().getDestination().getPosition());
                    } else {
                        System.out.printf("%8s ", "🪜:" + board[i][j].getObstacle().getDestination().getPosition());
                    }
                } else {
                    System.out.printf("%8d ", board[i][j].getPosition());
                }
            }
            System.out.println();
        }
        System.out.println("=".repeat(col * 10 + 1) + "\n");
    }
}
