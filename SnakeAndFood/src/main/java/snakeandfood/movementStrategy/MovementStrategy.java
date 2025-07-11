package snakeandfood.movementStrategy;

import snakeandfood.utils.Cell;
import snakeandfood.enums.Directions;

public interface MovementStrategy {
    public Cell getNextPosition(Cell currHeadPosition, Directions direction);
}
