package Chess_V1.utils;

import Chess_V1.pieceFactory.Piece;

public class Cell {
    private int row;
    private int col;
    private Piece piece;

    public Cell(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
