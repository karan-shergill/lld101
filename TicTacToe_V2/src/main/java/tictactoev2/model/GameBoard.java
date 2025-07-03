package tictactoev2.model;

public class GameBoard {
    int size;
    PlayingPiece[][] board;

    public GameBoard(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public int getSize() {
        return size;
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && this.board[row][col] == null;
    }

    public void printGameBoard() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.print(this.board[i][j] == null ? "_" : this.board[i][j]);
                if (j<size-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public boolean hasEmptyCell() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (board[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }
}
