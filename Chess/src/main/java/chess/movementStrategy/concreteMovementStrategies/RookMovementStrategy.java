package chess.movementStrategy.concreteMovementStrategies;

import chess.utils.Board;
import chess.utils.Cell;
import chess.movementStrategy.MovementStrategy;

public class RookMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        int x = Math.abs(startCell.getRow() - endCell.getRow());
        int y = Math.abs(startCell.getCol() - endCell.getCol());

        if ((x==0 && y>0) || (x>0 && y==0))
            return true;
        return false;
    }
}
