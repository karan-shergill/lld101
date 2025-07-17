package chess.pieceFactory;

import chess.utils.Board;
import chess.utils.Cell;
import chess.movementStrategy.MovementStrategy;

public abstract class Piece {
    private boolean isPieceWhite;
    private boolean isPieceKilled;
    private MovementStrategy movementStrategy;

    public Piece(boolean isPieceWhite) {
        this.isPieceWhite = isPieceWhite;
    }

    public boolean isPieceWhite() {
        return isPieceWhite;
    }

    public boolean isPieceKilled() {
        return isPieceKilled;
    }

    public void setPieceKilled(boolean pieceKilled) {
        isPieceKilled = pieceKilled;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return movementStrategy.canMove(board, startCell, endCell);
    }
}
