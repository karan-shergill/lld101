package snakeandfood_v1.movementStrategy;

import snakeandfood_v1.utils.Cell;
import snakeandfood_v1.enums.Directions;

import java.util.ArrayList;
import java.util.Random;

public class AIMovementStrategy implements MovementStrategy {
    @Override
    public Cell getNextPosition(Cell currHeadPosition, Directions direction) {
        // get all four direction and choose any in random
        int row = currHeadPosition.getRow();
        int col = currHeadPosition.getCol();

        ArrayList<Cell> allFourDirections = new ArrayList<>();
        allFourDirections.add(new Cell(row-1, col)); // UP
        allFourDirections.add(new Cell(row+1, col)); // DOWN
        allFourDirections.add(new Cell(row, col-1)); // LEFT
        allFourDirections.add(new Cell(row, col+1)); // RIGHT

        Random random = new Random();
        return allFourDirections.get(random.nextInt(allFourDirections.size()));
    }
}
