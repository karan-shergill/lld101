package practice.pieceType;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.strategy.KnightStrategyPiece;

public class Knight extends Piece {
    public Knight(PieceType pieceType, boolean isWhite) {
        super(pieceType, isWhite, new KnightStrategyPiece());
    }
}
