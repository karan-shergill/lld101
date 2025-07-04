package Chess_V1.pieceFactory.concretePieces;

import Chess_V1.movementStrategy.concreteMovementStrategies.KingMovementStrategy;
import Chess_V1.pieceFactory.Piece;

public class King extends Piece {
    public King(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new KingMovementStrategy());
    }
}
