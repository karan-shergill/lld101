package chess.pieceFactory.concretePieces;

import chess.movementStrategy.concreteMovementStrategies.RookMovementStrategy;
import chess.pieceFactory.Piece;

public class Rook extends Piece {
    public Rook(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new RookMovementStrategy());
    }
}
