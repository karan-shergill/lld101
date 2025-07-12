package chess.pieceFactory.concretePieces;

import chess.movementStrategy.concreteMovementStrategies.BishopMovementStrategy;
import chess.pieceFactory.Piece;

public class Bishop extends Piece {
    public Bishop(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new BishopMovementStrategy());
    }
}
