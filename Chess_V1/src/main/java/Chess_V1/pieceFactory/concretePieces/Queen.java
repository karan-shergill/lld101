package Chess_V1.pieceFactory.concretePieces;

import Chess_V1.movementStrategy.concreteMovementStrategies.QueenMovementStrategy;
import Chess_V1.pieceFactory.Piece;

public class Queen extends Piece {
    public Queen(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new QueenMovementStrategy());
    }
}
