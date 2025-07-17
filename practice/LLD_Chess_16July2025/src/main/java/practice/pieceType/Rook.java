package practice.pieceType;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.strategy.RookStrategyPiece;

public class Rook extends Piece {
    public Rook(PieceType pieceType, boolean isWhite) {
        super(pieceType, isWhite, new RookStrategyPiece());
    }
}
