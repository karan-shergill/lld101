package snakeandfood.foodFactory.foodItems;

import snakeandfood.utils.Cell;
import snakeandfood.foodFactory.FoodItem;

public class SuperFood extends FoodItem {
    public SuperFood(Cell cell) {
        super(cell);
        super.setPoints(2);
    }
}
