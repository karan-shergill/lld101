package snakesandladders_v1.obstacleFactory.obstacleTypes;

import snakesandladders_v1.Utils.Cell;
import snakesandladders_v1.obstacleFactory.Obstacle;
import snakesandladders_v1.obstacleFactory.ObstacleType;

public class Snake extends Obstacle {
    public Snake(Cell source, Cell destination) {
        super(source, destination);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.SNAKE;
    }
}
