package practice.pieceType;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.strategy.QueenStrategyPiece;

public class Queen extends Piece {
    public Queen(PieceType pieceType, boolean isWhite) {
        super(pieceType, isWhite, new QueenStrategyPiece());
    }
}
