package practice.modal;

import practice.constants.FoodType;

public abstract class Food {
    private FoodType foodType;

    public Food(FoodType foodType) {
        this.foodType = foodType;
    }

    public abstract int getFoodPoints();

    public FoodType getFoodType() {
        return foodType;
    }
}
