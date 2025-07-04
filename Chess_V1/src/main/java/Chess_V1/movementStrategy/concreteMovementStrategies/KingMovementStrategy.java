package Chess_V1.movementStrategy.concreteMovementStrategies;

import Chess_V1.utils.Board;
import Chess_V1.utils.Cell;
import Chess_V1.movementStrategy.MovementStrategy;

public class KingMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int x = Math.abs(startCell.getRow() - endCell.getRow());
        int y = Math.abs(startCell.getCol() - endCell.getCol());

        // King can move one square in any direction (including diagonals)
        if (x <= 1 && y <= 1 && (x + y > 0)) {
            return true;
        }

        if (isValidCastling(board, startCell, endCell)) {
            return true;
        }

        return false;
    }

    private boolean isValidCastling(Board board, Cell startCell, Cell endCell) {
        /*
        1. Check Rook is at correct position
        2. Check NO piece between Rook and King
        3. Check Rook & King not been moved before
        4. Check King move at this point won't be in attacking
        */
        return false;
    }
}
