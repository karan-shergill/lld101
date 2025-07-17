package practice.factory;

import practice.constants.PieceType;
import practice.modal.Piece;
import practice.pieceType.Pawn;

public class PieceFactory {
    public static Piece getPieceOfType(PieceType pieceType, boolean isWhite) {
        switch (pieceType) {
            case PAWN -> {
                return new Pawn(PieceType.PAWN, isWhite);
            }
            case ROOK -> {
                return new Pawn(PieceType.ROOK, isWhite);
            }
            case BISHOP -> {
                return new Pawn(PieceType.BISHOP, isWhite);
            }
            case KNIGHT -> {
                return new Pawn(PieceType.KNIGHT, isWhite);
            }
            case KING -> {
                return new Pawn(PieceType.KING, isWhite);
            }
            case QUEEN -> {
                return new Pawn(PieceType.QUEEN, isWhite);
            }
        }
        return null;
    }
}
