package snakeandfood;

import snakeandfood.game.allGames.SnakeAndFoodGame;
import snakeandfood.movementStrategy.HumanMovementStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to board games!");

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Provide game board size: (Width,Height)");
        int width = inputScanner.nextInt();
        int height = inputScanner.nextInt();

        int[][] food = provideFoodLocation();

        SnakeAndFoodGame snakeAndFoodGame = new SnakeAndFoodGame(
                height,
                width,
                food,
                new HumanMovementStrategy()
        );
        snakeAndFoodGame.startGame();
    }

    private static int[][] provideFoodLocation() {
        // food can be placed predefined or dynamically using Random()
        return  new int[][]{{1,1}, {2,2}};
    }
}