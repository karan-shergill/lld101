package Chess_V1.pieceFactory.concretePieces;

import Chess_V1.movementStrategy.concreteMovementStrategies.RookMovementStrategy;
import Chess_V1.pieceFactory.Piece;

public class Rook extends Piece {
    public Rook(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new RookMovementStrategy());
    }
}
