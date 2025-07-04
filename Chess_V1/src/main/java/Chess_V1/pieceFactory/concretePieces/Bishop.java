package Chess_V1.pieceFactory.concretePieces;

import Chess_V1.movementStrategy.concreteMovementStrategies.BishopMovementStrategy;
import Chess_V1.pieceFactory.Piece;

public class Bishop extends Piece {
    public Bishop(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new BishopMovementStrategy());
    }
}
