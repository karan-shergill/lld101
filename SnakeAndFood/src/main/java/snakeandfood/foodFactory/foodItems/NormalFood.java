package snakeandfood.foodFactory.foodItems;

import snakeandfood.utils.Cell;
import snakeandfood.foodFactory.FoodItem;

public class NormalFood extends FoodItem {
    public NormalFood(Cell cell) {
        super(cell);
        super.setPoints(1);
    }
}
