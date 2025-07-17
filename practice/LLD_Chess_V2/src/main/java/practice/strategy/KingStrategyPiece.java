package practice.strategy;

import practice.modal.Cell;

public class KingStrategyPiece implements PiecePlayStrategy {
    @Override
    public boolean isValidMove(Cell from, Cell to) {
        if (from.getRow()!=to.getRow() || from.getCol()!=to.getCol()) {
            if (Math.abs(from.getRow()-to.getRow()) + Math.abs(from.getCol()-to.getCol()) == 2) {
                // digonal 1 step move
                return true;
            }
            if (from.getRow() == to.getRow() && (Math.abs(from.getCol()-to.getCol()) == 1)) {
                return true;
            }
            if (from.getCol() == to.getCol() && (Math.abs(from.getRow()-to.getRow()) == 1)) {
                return true;
            }
        }
        return false;
    }
}
