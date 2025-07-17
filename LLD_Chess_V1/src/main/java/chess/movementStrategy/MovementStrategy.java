package chess.movementStrategy;

import chess.utils.Board;
import chess.utils.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
