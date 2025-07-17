package practice.strategy;

import practice.modal.Cell;

public class RookStrategyPiece implements PiecePlayStrategy {
    @Override
    public boolean isValidMove(Cell from, Cell to) {
        if (from.getRow()==to.getRow() && from.getCol()!= to.getCol()) {
            // same row, diff col
            return true;
        }
        if (from.getCol()==to.getCol() && from.getRow()!= to.getRow()) {
            // same col, diff row
            return true;
        }
        return true;
    }
}
