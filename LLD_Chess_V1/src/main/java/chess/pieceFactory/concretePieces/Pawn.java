package chess.pieceFactory.concretePieces;

import chess.movementStrategy.concreteMovementStrategies.PawnMovementStrategy;
import chess.pieceFactory.Piece;

public class Pawn extends Piece {
    public Pawn(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new PawnMovementStrategy());
    }
}
