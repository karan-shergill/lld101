package snakesandladders.obstacleFactory.obstacleTypes;

import snakesandladders.Utils.Cell;
import snakesandladders.obstacleFactory.Obstacle;
import snakesandladders.obstacleFactory.ObstacleType;

public class Ladder extends Obstacle {
    public Ladder(Cell source, Cell destination) {
        super(source, destination);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.LADDER;
    }
}
