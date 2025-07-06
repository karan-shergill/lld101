package snakesandladders_v1.obstacleFactory.obstacleTypes;

import snakesandladders_v1.Utils.Cell;
import snakesandladders_v1.obstacleFactory.Obstacle;
import snakesandladders_v1.obstacleFactory.ObstacleType;

public class Ladder extends Obstacle {
    public Ladder(Cell source, Cell destination) {
        super(source, destination);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.LADDER;
    }
}
