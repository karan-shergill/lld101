package practice.modal;

import practice.constants.PieceType;
import practice.factory.PieceFactory;

public class Board {
    public static Board boardInstance;
    private Cell[][] board;

    private Board() {
        board = new Cell[8][8];
    }

    public static Board getBoardInstance() {
        if (boardInstance == null) {
            boardInstance = new Board();
        }
        boardInstance.initializeBoard();
        return boardInstance;
    }

    private void initializeBoard() {
        // place black piece
        board[0][0] = new Cell(0, 0, PieceFactory.getPieceOfType(PieceType.ROOK, false));
        board[0][1] = new Cell(0, 1, PieceFactory.getPieceOfType(PieceType.KNIGHT, false));
        board[0][2] = new Cell(0, 2, PieceFactory.getPieceOfType(PieceType.BISHOP, false));
        board[0][3] = new Cell(0, 3, PieceFactory.getPieceOfType(PieceType.QUEEN, false));
        board[0][4] = new Cell(0, 4, PieceFactory.getPieceOfType(PieceType.KING, false));
        board[0][5] = new Cell(0, 5, PieceFactory.getPieceOfType(PieceType.BISHOP, false));
        board[0][6] = new Cell(0, 6, PieceFactory.getPieceOfType(PieceType.KNIGHT, false));
        board[0][7] = new Cell(0, 7, PieceFactory.getPieceOfType(PieceType.ROOK, false));
        placePawn(1, false);

        // place white piece
        board[7][0] = new Cell(7, 0, PieceFactory.getPieceOfType(PieceType.ROOK, true));
        board[7][1] = new Cell(7, 1, PieceFactory.getPieceOfType(PieceType.KNIGHT, true));
        board[7][2] = new Cell(7, 2, PieceFactory.getPieceOfType(PieceType.BISHOP, true));
        board[7][3] = new Cell(7, 3, PieceFactory.getPieceOfType(PieceType.QUEEN, true));
        board[7][4] = new Cell(7, 4, PieceFactory.getPieceOfType(PieceType.KING, true));
        board[7][5] = new Cell(7, 5, PieceFactory.getPieceOfType(PieceType.BISHOP, true));
        board[7][6] = new Cell(7, 6, PieceFactory.getPieceOfType(PieceType.KNIGHT, true));
        board[7][7] = new Cell(7, 7, PieceFactory.getPieceOfType(PieceType.ROOK, true));
        placePawn(6, true);

        // place rest of the cell on board
        placeEmptyCellOnBoard();
    }

    private void placeEmptyCellOnBoard() {
        for (int row=2; row<8; row++) {
            for (int col=0; col<8; col++) {
                board[row][col] = new Cell(row, col, null);
            }
        }
    }

    private void placePawn(int row, boolean isWhite) {
        for (int col=0; col<8; col++) {
            board[row][col] = new Cell(row, col, PieceFactory.getPieceOfType(PieceType.PAWN, isWhite));
        }
    }

    public void print() {
        for (int row=0; row<8; row++) {
            for (int col=0; col<8; col++) {
                // PRINT the board
            }
        }
    }

    private boolean isPositionOnBoard(int row, int col) {
        if (row<0 || row>=8 || col<0 || col>=8) {
            System.out.println("Position outside game board!");
            return false;
        }
        return true;
    }

    public boolean isValidPiecePositionFrom(int row1, int col1, boolean isWhite) {
        if (!isPositionOnBoard(row1, col1)) {
            return false;
        }
        if (board[row1][col1] == null) {
            System.out.println("No playing piece at FROM position");
            return false;
        }
        if (board[row1][col1].getPiece().isWhite() != isWhite) {
            System.out.println("Choose your color piece to play");
            return false;
        }
        return true;
    }

    public boolean isValidPiecePositionTo(int row2, int col2, boolean isWhite) {
        if (!isPositionOnBoard(row2, col2)) {
            return false;
        }
        if (board[row2][col2] != null && board[row2][col2].getPiece().isWhite() == isWhite) {
            System.out.println("Can't kill own teammate");
            return false;
        }
        return true;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void movePlayed(Move move) {
        if (move.getTo().getPiece() != null) {
            System.out.println(move.getTo().getPiece() + "killed" + move.getTo().getPiece());
        }
        move.getTo().setPiece(move.getPiece());
        move.getFrom().setPiece(null);
    }
}
