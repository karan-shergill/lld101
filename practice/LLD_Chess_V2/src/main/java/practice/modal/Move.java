package practice.modal;

public class Move {
    private Cell from;
    private Cell to;
    private Piece piece;

    public Move(Cell from, Cell to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }
}
