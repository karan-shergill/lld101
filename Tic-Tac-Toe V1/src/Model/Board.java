package Model;

public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int n) {
        this.size = n;
        board = new PlayingPiece[n][n];
    }

    public void printGameBoard() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (board[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j].pieceType);
                }

                if (j < size-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public boolean noFreeSpaceOnGameBoard() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
        if (row<0 || row>=size || col<0 || col>=size) {
            return false;
        }
        if (board[row][col] == null) {
            board[row][col] = playingPiece;
            return true;
        }
        return false;
    }
}
