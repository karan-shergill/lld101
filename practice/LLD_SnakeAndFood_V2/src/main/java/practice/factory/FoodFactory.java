package practice.factory;

import practice.constants.FoodType;
import practice.foods.NormalFood;
import practice.foods.PoisonsFood;
import practice.foods.SuperFood;
import practice.modal.Food;

public class FoodFactory {
    public static Food getFoodOfType(FoodType foodType) {
        switch (foodType) {
            case SUPER_FOOD -> {
                return new SuperFood(foodType);
            }
            case NORMAL_FOOD -> {
                return new NormalFood(foodType);
            }
            case POISON_FOOD -> {
                return new PoisonsFood(foodType);
            }
        }
        return null;
    }
}
