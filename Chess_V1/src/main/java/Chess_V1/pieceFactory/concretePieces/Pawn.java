package Chess_V1.pieceFactory.concretePieces;

import Chess_V1.movementStrategy.concreteMovementStrategies.PawnMovementStrategy;
import Chess_V1.pieceFactory.Piece;

public class Pawn extends Piece {
    public Pawn(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new PawnMovementStrategy());
    }
}
