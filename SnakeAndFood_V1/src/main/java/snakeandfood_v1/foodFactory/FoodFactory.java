package snakeandfood_v1.foodFactory;

import snakeandfood_v1.utils.Cell;
import snakeandfood_v1.foodFactory.foodItems.NormalFood;
import snakeandfood_v1.foodFactory.foodItems.PoisonousFood;
import snakeandfood_v1.foodFactory.foodItems.SuperFood;

public class FoodFactory {
    public static FoodItem getFood(FoodItemType foodItemType, Cell foodLocation) {
        switch (foodItemType) {
            case NORMAL -> {
                return new NormalFood(foodLocation);
            }
            case SUPER -> {
                return new SuperFood(foodLocation);
            }
            case POISONOUS -> {
                return new PoisonousFood(foodLocation);
            }
        }
        return null;
    }
}
