package practice.modal;

import practice.constants.PieceType;
import practice.strategy.PiecePlayStrategy;

public abstract class Piece {
    private PieceType pieceType;
    private  boolean isWhite;
    private PiecePlayStrategy piecePlayStrategy;

    public Piece(PieceType pieceType, boolean isWhite, PiecePlayStrategy piecePlayStrategy) {
        this.pieceType = pieceType;
        this.isWhite = isWhite;
        this.piecePlayStrategy = piecePlayStrategy;
    }

    public PieceType getChessPiece() {
        return pieceType;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public PiecePlayStrategy getPiecePlayStrategy() {
        return piecePlayStrategy;
    }
}
