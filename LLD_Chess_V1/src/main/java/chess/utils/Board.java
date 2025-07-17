package chess.utils;

import chess.pieceFactory.PieceFactory;
import chess.pieceFactory.PieceType;

public class Board {
    private int size;
    private Cell[][] board;

    private static Board boardInstance;

    // Single instance of board -> Singleton Design Pattern
    public static Board getBoardInstance(int size) {
        if (boardInstance == null) {
            boardInstance = new Board(size);
        }
        return boardInstance;
    }

    private Board(int size) {
        this.size = size;
        board = new Cell[size][size];
        initializeChessBoard();
    }

    private void initializeChessBoard() {
        // setting white pieces (bottom of board)
        setPieceRow(7, true);
        setPawnRow(6, true);

        // setting black pieces (top of board)
        setPieceRow(0, false);
        setPawnRow(1, false);

        // rest cell has no pieces
        for (int row=2; row<6; row++) {
            for (int col=0; col<this.size; col++) {
                board[row][col] = new Cell(row, col, null);
            }
        }
    }

    private void setPieceRow(int row, boolean isWhite) {
        board[row][0] = new Cell(row, 0, PieceFactory.createPiece(PieceType.ROOK, isWhite));
        board[row][1] = new Cell(row, 1, PieceFactory.createPiece(PieceType.KNIGHT, isWhite));
        board[row][2] = new Cell(row, 2, PieceFactory.createPiece(PieceType.BISHOP, isWhite));
        board[row][3] = new Cell(row, 3, PieceFactory.createPiece(PieceType.QUEEN, isWhite));
        board[row][4] = new Cell(row, 4, PieceFactory.createPiece(PieceType.KING, isWhite));
        board[row][5] = new Cell(row, 5, PieceFactory.createPiece(PieceType.BISHOP, isWhite));
        board[row][6] = new Cell(row, 6, PieceFactory.createPiece(PieceType.KNIGHT, isWhite));
        board[row][7] = new Cell(row, 7, PieceFactory.createPiece(PieceType.ROOK, isWhite));
    }

    private void setPawnRow(int row, boolean isWhite) {
        for (int col=0; col<this.size; col++) {
            this.board[row][col] = new Cell(row, col, PieceFactory.createPiece(PieceType.PAWN, isWhite));
        }
    }

    public Cell getCell(int row, int col) {
        if (row>=0 && row<this.size && col>=0 && col<this.size) {
            return this.board[row][col];
        }
        return null;
    }
}
