package practice.foods;

import practice.constants.FoodType;
import practice.modal.Food;

public class PoisonsFood extends Food {
    public PoisonsFood(FoodType foodType) {
        super(foodType);
    }

    @Override
    public int getFoodPoints() {
        return -1;
    }
}
