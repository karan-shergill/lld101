package snakesandladders_v1.obstacleFactory;

import snakesandladders_v1.Utils.Cell;
import snakesandladders_v1.obstacleFactory.obstacleTypes.Ladder;
import snakesandladders_v1.obstacleFactory.obstacleTypes.Snake;

public class ObstacleFactory {
    public static Obstacle getObstacle(ObstacleType obstacleType, Cell source, Cell destination) {
        // Input validation
        if (obstacleType == null) {
            throw new IllegalArgumentException("Obstacle type cannot be null");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source cell cannot be null");
        }
        if (destination == null) {
            throw new IllegalArgumentException("Destination cell cannot be null");
        }
        
        switch (obstacleType) {
            case SNAKE -> {
                return new Snake(source, destination);
            }
            case LADDER -> {
                return new Ladder(source, destination);
            }
            default -> {
                throw new IllegalArgumentException("Unknown obstacle type: " + obstacleType);
            }
        }
    }
}
