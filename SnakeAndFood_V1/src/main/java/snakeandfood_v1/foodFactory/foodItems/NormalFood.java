package snakeandfood_v1.foodFactory.foodItems;

import snakeandfood_v1.utils.Cell;
import snakeandfood_v1.foodFactory.FoodItem;

public class NormalFood extends FoodItem {
    public NormalFood(Cell cell) {
        super(cell);
        super.setPoints(1);
    }
}
