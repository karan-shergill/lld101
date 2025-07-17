package practice.modal;

import practice.constants.FoodType;

public class Cell {
    private int row;
    private int col;
    private boolean hasSnakeBody; // helps in rendering gameboard
    private boolean hasSnakeHead;
    private Food food;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setHasSnakeBody(boolean hasSnakeBody) {
        this.hasSnakeBody = hasSnakeBody;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHasSnakeBody() {
        return hasSnakeBody;
    }


    public boolean isHasSnakeHead() {
        return hasSnakeHead;
    }

    public void setHasSnakeHead(boolean hasSnakeHead) {
        this.hasSnakeHead = hasSnakeHead;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
