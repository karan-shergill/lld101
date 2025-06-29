package tictactoev1.model;

public class PlayingBoard {
    private int boardSize;
    private PlayingPiece[][] board;

    public PlayingBoard(int size) {
        this.boardSize = size;
        board = new PlayingPiece[size][size];
    }

    public void printBoard() {
        for (int i=0; i<boardSize; i++) {
            for (int j=0; j<boardSize; j++) {
                if (board[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j].getPlayingPieceType());
                }

                if (j < boardSize - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public boolean makeMove(PlayingPiece playingPiece, int row, int col) {
        if (row<0 || row>=boardSize || col<0 || col>=boardSize) {
            return false;
        }

        if (board[row][col] != null) {
            return  false;
        }

        board[row][col] = playingPiece;
        return  true;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }
}
