package snakeandfood_v1.foodFactory.foodItems;

import snakeandfood_v1.utils.Cell;
import snakeandfood_v1.foodFactory.FoodItem;

public class PoisonousFood extends FoodItem {
    public PoisonousFood(Cell cell) {
        super(cell);
        this.setPoints(-1);
    }
}
