package practice.pieceType;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.strategy.PawnStrategyPiece;

public class Pawn extends Piece {
    public Pawn(PieceType pieceType, boolean isWhite) {
        super(pieceType, isWhite, new PawnStrategyPiece());
    }
}
