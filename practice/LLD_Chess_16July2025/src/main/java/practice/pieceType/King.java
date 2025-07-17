package practice.pieceType;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.strategy.KingStrategyPiece;

public class King extends Piece {
    public King(PieceType pieceType, boolean isWhite) {
        super(pieceType, isWhite, new KingStrategyPiece());
    }
}
