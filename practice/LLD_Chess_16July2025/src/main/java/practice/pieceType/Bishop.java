package practice.pieceType;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.strategy.BishopStrategyPiece;

public class Bishop extends Piece {
    public Bishop(PieceType pieceType, boolean isWhite) {
        super(pieceType, isWhite, new BishopStrategyPiece());

    }
}
