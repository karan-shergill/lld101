package chess.pieceFactory.concretePieces;

import chess.movementStrategy.concreteMovementStrategies.KingMovementStrategy;
import chess.pieceFactory.Piece;

public class King extends Piece {
    public King(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new KingMovementStrategy());
    }
}
