package snakeandfood.movementStrategy;

import snakeandfood.utils.Cell;
import snakeandfood.enums.Directions;

public class HumanMovementStrategy implements MovementStrategy{
    @Override
    public Cell getNextPosition(Cell currHeadPosition, Directions direction) {
        int row = currHeadPosition.getRow();
        int col = currHeadPosition.getCol();

        switch (direction) {
            case UP -> {
                return new Cell(row-1, col);
            }
            case DOWN -> {
                return new Cell(row+1, col);
            }
            case LEFT -> {
                return new Cell(row, col-1);
            }
            case RIGHT -> {
                return new Cell(row, col+1);
            }
        }
        return null;
    }
}
