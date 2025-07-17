package chess.utils;

public class Move {
    private Cell startCell;
    private Cell endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public boolean isValidMove() {
        if (this.endCell == null) {
            // As destination cell is empty, Piece can move there
            return true;
        }

        if (startCell.getPiece().isPieceWhite() == endCell.getPiece().isPieceWhite()) {
            // Both start and end piece are same
            // it's not possible to kill own teammates
            return false;
        }
        return true;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }
}
