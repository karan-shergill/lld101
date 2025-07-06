package snakesandladders_v1.Utils;

import snakesandladders_v1.obstacleFactory.Obstacle;

public class Cell {
    private int row;
    private int col;
    private int position;
    private Obstacle obstacle;
    private boolean hasObstacle;

    public Cell(int row, int col, int position) {
        this.row = row;
        this.col = col;
        this.position = position;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPosition() {
        return position;
    }

    public boolean isHasObstacle() {
        return hasObstacle;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setHasObstacle(boolean hasObstacle) {
        this.hasObstacle = hasObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}
