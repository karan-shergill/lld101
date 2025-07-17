package practice.strategy;

import practice.constants.PieceType;
import practice.modal.Cell;

public class PawnStrategyPiece implements PiecePlayStrategy {
    @Override
    public boolean isValidMove(Cell from, Cell to) {
        if (from.getCol() == to.getCol()) {
            // same col
            if (from.getRow()==1 && (to.getRow()==2 || to.getRow()==3)) {
                // if in its initial row, PAWN can play 1/2 steps - BLACk piece
                return  true;
            }
            if (from.getRow()==6 && (to.getRow()==5 || to.getRow()==4)) {
                // if in its initial row, PAWN can play 1/2 steps - WHITE piece
                return  true;
            }
            if (to.getRow() == from.getRow()+1 && !from.getPiece().isWhite()) {
                // BLACK row can only inc
                return true;
            }
            if (to.getRow() == from.getRow()-1 && from.getPiece().isWhite()) {
                // BLACK row can only dec
                return true;
            }
            return false;
        } else {
            // PAWN is not going straight
            if ((to.getCol() != from.getCol()-1) || (to.getCol() != from.getCol()+1)) {
                return false;
            }
            if (to.getPiece() == null) {
                // if not killing PAWN can only go in straight line
                return false;
            }
            return true;
        }
    }
}
