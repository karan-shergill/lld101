package practice;

import practice.service.Games;
import practice.service.SnakeAndFoodGame;

public class Main {
    public static void main(String[] args) {
            Games snakeAndFood = new SnakeAndFoodGame(5, 5);
            snakeAndFood.play();
    }
}