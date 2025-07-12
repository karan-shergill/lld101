package chess.pieceFactory.concretePieces;

import chess.movementStrategy.concreteMovementStrategies.QueenMovementStrategy;
import chess.pieceFactory.Piece;

public class Queen extends Piece {
    public Queen(boolean isPieceWhite) {
        super(isPieceWhite);
        super.setMovementStrategy(new QueenMovementStrategy());
    }
}
