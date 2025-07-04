package Chess_V1.pieceFactory.concretePieces;

import Chess_V1.movementStrategy.concreteMovementStrategies.KnightMovementStrategy;
import Chess_V1.pieceFactory.Piece;

public class Knight extends Piece {
    public Knight(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new KnightMovementStrategy());
    }
}
