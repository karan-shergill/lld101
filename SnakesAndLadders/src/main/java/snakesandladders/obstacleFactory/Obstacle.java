package snakesandladders.obstacleFactory;

import snakesandladders.Utils.Cell;

public abstract class Obstacle {
    private Cell source;
    private Cell destination;

    public Obstacle(Cell source, Cell destination) {
        this.source = source;
        this.destination = destination;
    }

    public abstract ObstacleType getObstacleType();

    public Cell getSource() {
        return source;
    }

    public Cell getDestination() {
        return destination;
    }
}
