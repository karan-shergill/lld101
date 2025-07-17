package practice.models;

import practice.constants.PlayingSymbol;

public class Board {
    private int size;
    private static Cell[][] board;
    public static Board boardInstance;

    private Board(int size) {
        this.size = size;
    }

    public synchronized static Board getBoardInstance(int size) {
        if (boardInstance == null) {
            boardInstance = new Board(size);
            boardInstance.initializeBoard();
        }
        return boardInstance;
    }

    private void initializeBoard() {
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    private boolean isValidMove(Cell cell, PlayingSymbol playingSymbol) {
        int row = cell.getRow();
        int col = cell.getCol();

        if (!(row>=0 && row<size && col>=0 && col<size)) {
            System.out.println("Invalid row & col selected! Board size is " + size + "*" + size + ", and choice was " + row + "*" + col);
            return false;
        }

        if (board[row][col] != null) {
            System.out.println("Selected row & col is not empty!");
            return false;
        }

        return true;
    }

    public boolean playMove(Cell cell, PlayingSymbol playingSymbol) {
        if (isValidMove(cell, playingSymbol)) {
            board[cell.getRow()][cell.getCol()] = cell;
            cell.setPlayingSymbol(playingSymbol);
            return true;
        }
        return false;
    }

    public boolean isWon(Cell cell, PlayingSymbol playingSymbol) {
        int row = cell.getRow();
        int col = cell.getCol();
        //Check row all same as playingSymbol
        //Check col all same as playingSymbol

        if (row == col) {
            // Check diagonal all same as playingSymbol
            // Check anti-diagonal all same as playingSymbol
        }
        return true;
    }

    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                if (cell != null && cell.getPlayingSymbol() != null) {
                    System.out.print(cell.getPlayingSymbol() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null || board[i][j].getPlayingSymbol() == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
