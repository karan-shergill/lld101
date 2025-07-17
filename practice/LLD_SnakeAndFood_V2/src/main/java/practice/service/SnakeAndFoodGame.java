package practice.service;

import practice.constants.Direction;
import practice.constants.GameStatus;
import practice.modal.Board;
import practice.modal.Cell;
import practice.utils.Util;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class SnakeAndFoodGame implements Games {
    Deque<Cell> snake;
    HashSet<Cell> snakeBody;
    Cell snakeHead;
    GameStatus gameStatus;
    Board gameBoard;
    int score;
    Scanner scanner;

    public SnakeAndFoodGame(int height, int width) {
        gameBoard = Board.getBoardInstance(height, width);
        snake = new LinkedList<>();
        snakeBody = new HashSet<>();
        gameStatus = GameStatus.START;
        score = 0;
        scanner = new Scanner(System.in);
        initializeSnake();
    }

    private void initializeSnake() {
        // By default, snake start from 0,0
        Cell startCell = gameBoard.getBoard()[0][0];
        startCell.setHasSnakeHead(true);
        snake.addFirst(startCell);
        snakeBody.add(startCell);
        snakeHead = startCell;
    }

    @Override
    public void play() {
        gameStatus = GameStatus.IN_PROGRESS;
        while (gameStatus == GameStatus.IN_PROGRESS) {
            Util.addFood(gameBoard, snakeBody);
            gameBoard.printBoard();

            Direction direction = Util.getInputFromUser(scanner);
            Cell newSnakeHead = Util.getNewSnakeHead(snakeHead, direction, gameBoard);

            if (gameBoard.isSnakeHeadCollidedToBorder(newSnakeHead)) {
                System.out.println("Snake collider to border!");
                break;
            }

            if (hasSnakeCollidedToItsBody(newSnakeHead)) {
                System.out.println("Snake Collided to its own body");
                break;
            }

            if (newSnakeHead.getFood() != null) {
                score += newSnakeHead.getFood().getFoodPoints();

                // Update current head to body
                snakeHead.setHasSnakeHead(false);
                snakeHead.setHasSnakeBody(true);

                // Add new head (don't remove tail - snake grows)
                newSnakeHead.setHasSnakeHead(true);
                newSnakeHead.setFood(null); // Clear consumed food
                snake.addFirst(newSnakeHead);
                snakeBody.add(newSnakeHead);
                snakeHead = newSnakeHead;
            } else {
                // Remove tail first
                Cell tail = snake.getLast();
                tail.setHasSnakeBody(false);
                snake.removeLast();
                snakeBody.remove(tail);

                // Update current head to body (if snake has more than 1 segment)
                if (!snake.isEmpty()) {
                    snakeHead.setHasSnakeHead(false);
                    snakeHead.setHasSnakeBody(true);
                }

                // Add new head
                newSnakeHead.setHasSnakeHead(true);
                newSnakeHead.setFood(null); // Clear consumed food
                snake.addFirst(newSnakeHead);
                snakeBody.add(newSnakeHead);
                snakeHead = newSnakeHead;
            }
        }
        System.out.println("Score:" + score);
    }

    private boolean hasSnakeCollidedToItsBody(Cell snakeHead) {
        if (snakeBody.contains(snakeHead)) {
            return true;
        }
        return false;
    }
}
