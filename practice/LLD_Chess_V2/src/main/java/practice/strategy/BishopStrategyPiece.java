package practice.strategy;

import practice.modal.Cell;

public class BishopStrategyPiece implements PiecePlayStrategy {
    @Override
    public boolean isValidMove(Cell from, Cell to) {
        if (Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getCol() - to.getCol())) {
            return true;
        }
        return false;
    }
}
