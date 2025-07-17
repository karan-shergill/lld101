package practice.utils;

import practice.constants.Direction;
import practice.constants.FoodType;
import practice.factory.FoodFactory;
import practice.modal.Board;
import practice.modal.Cell;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Util {
    public static Direction getInputFromUser(Scanner scanner) {
        System.out.println("Enter direction: W/A/S/D");
        String input = scanner.nextLine();
        switch (input) {
            case "W" -> {
                return Direction.UP;
            }
            case "S" -> {
                return Direction.DOWN;
            }
            case "A" -> {
                return  Direction.LEFT;
            }
            case "D" -> {
                return Direction.RIGHT;
            }
        }
        return null;
    }

    public static Cell getNewSnakeHead(Cell currHead, Direction direction, Board board) {
        int row = currHead.getRow();
        int col = currHead.getCol();

        switch (direction) {
            case UP -> row--;
            case DOWN -> row++;
            case LEFT -> col--;
            case RIGHT -> col++;
        }

        return board.getBoard()[row][col]; // Use existing cell
    }

    public static void addFood(Board board, HashSet<Cell> snakeBody) {
        Random random = new Random();
        int foodType = random.nextInt(1,4);

        int row, col;
        while (true) {
            row = random.nextInt(0, board.getHeight());
            col = random.nextInt(0, board.getWidth());

            if (snakeBody.contains(new Cell(row, col))) {
                // Food can't be on snake body
                continue;
            }
            break;
        }

        switch (foodType) {
            case 1 -> {
                board.getBoard()[row][col].setFood(FoodFactory.getFoodOfType(FoodType.NORMAL_FOOD));
            }
            case 2 -> {
                board.getBoard()[row][col].setFood(FoodFactory.getFoodOfType(FoodType.SUPER_FOOD));
            }
            case 3 -> {
                board.getBoard()[row][col].setFood(FoodFactory.getFoodOfType(FoodType.POISON_FOOD));
            }
        }
    }
}
