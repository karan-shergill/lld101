package snakeandfood.foodFactory;

import snakeandfood.utils.Cell;
import snakeandfood.foodFactory.foodItems.NormalFood;
import snakeandfood.foodFactory.foodItems.PoisonousFood;
import snakeandfood.foodFactory.foodItems.SuperFood;

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
