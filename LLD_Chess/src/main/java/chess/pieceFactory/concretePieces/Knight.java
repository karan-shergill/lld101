package chess.pieceFactory.concretePieces;

import chess.movementStrategy.concreteMovementStrategies.KnightMovementStrategy;
import chess.pieceFactory.Piece;

public class Knight extends Piece {
    public Knight(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new KnightMovementStrategy());
    }
}
