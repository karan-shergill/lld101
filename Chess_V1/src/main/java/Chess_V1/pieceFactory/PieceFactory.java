package Chess_V1.pieceFactory;
import Chess_V1.pieceFactory.concretePieces.*;

public class PieceFactory {
    public static Piece createPiece(PieceType pieceType, boolean isWhite) {
        switch (pieceType) {
            case KING -> {
                return new King(isWhite);
            }
            case QUEEN -> {
                return new Queen(isWhite);
            }
            case PAWN -> {
                return new Pawn(isWhite);
            }
            case KNIGHT -> {
                return new Knight(isWhite);
            }
            case ROOK -> {
                return new Rook(isWhite);
            }
            case BISHOP -> {
                return new Bishop(isWhite);
            }
        }
        return null;
    }

}
