package Chess_V1.movementStrategy;

import Chess_V1.utils.Board;
import Chess_V1.utils.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
