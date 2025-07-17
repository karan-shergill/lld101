package practice.foods;

import practice.constants.FoodType;
import practice.modal.Food;

public class SuperFood extends Food {
    public SuperFood(FoodType foodType) {
        super(foodType);
    }

    @Override
    public int getFoodPoints() {
        return 2;
    }
}
