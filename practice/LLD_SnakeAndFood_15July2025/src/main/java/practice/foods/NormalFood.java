package practice.foods;

import practice.constants.FoodType;
import practice.modal.Food;

public class NormalFood extends Food {
    public NormalFood(FoodType foodType) {
        super(foodType);
    }

    @Override
    public int getFoodPoints() {
        return 1;
    }
}
