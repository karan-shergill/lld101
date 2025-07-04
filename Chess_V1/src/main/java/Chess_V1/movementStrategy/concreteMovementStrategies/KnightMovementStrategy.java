package Chess_V1.movementStrategy.concreteMovementStrategies;

import Chess_V1.utils.Board;
import Chess_V1.utils.Cell;
import Chess_V1.movementStrategy.MovementStrategy;

public class KnightMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int x = Math.abs(startCell.getRow() - endCell.getRow());
        int y = Math.abs(startCell.getCol() - endCell.getCol());

        // When Knight moves to a valid end box from a start box
        // The index x and y will always follow the rule x*y = 2
        if (x*y == 2) {
            return true;
        }
        return false;
    }
}
