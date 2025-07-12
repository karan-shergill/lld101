package snakeandfood.foodFactory.foodItems;

import snakeandfood.utils.Cell;
import snakeandfood.foodFactory.FoodItem;

public class PoisonousFood extends FoodItem {
    public PoisonousFood(Cell cell) {
        super(cell);
        this.setPoints(-1);
    }
}
