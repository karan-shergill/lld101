package snakeandfood_v1.movementStrategy;

import snakeandfood_v1.utils.Cell;
import snakeandfood_v1.enums.Directions;

public interface MovementStrategy {
    public Cell getNextPosition(Cell currHeadPosition, Directions direction);
}
