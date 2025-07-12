package chess.movementStrategy.concreteMovementStrategies;

import chess.utils.Board;
import chess.utils.Cell;
import chess.movementStrategy.MovementStrategy;

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
