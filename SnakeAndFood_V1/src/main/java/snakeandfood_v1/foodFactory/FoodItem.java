package snakeandfood_v1.foodFactory;

import snakeandfood_v1.utils.Cell;

public abstract class FoodItem {
    private Cell cell;
    private int points;

    public FoodItem(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
