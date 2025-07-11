package snakesandladders.obstacleFactory.obstacleTypes;

import snakesandladders.Utils.Cell;
import snakesandladders.obstacleFactory.Obstacle;
import snakesandladders.obstacleFactory.ObstacleType;

public class Snake extends Obstacle {
    public Snake(Cell source, Cell destination) {
        super(source, destination);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.SNAKE;
    }
}
