package snakeandfood_v1.game.allGames;

import snakeandfood_v1.utils.Board;
import snakeandfood_v1.utils.Cell;
import snakeandfood_v1.utils.Snake;
import snakeandfood_v1.enums.Directions;
import snakeandfood_v1.enums.GameStatus;
import snakeandfood_v1.game.BoardGame;
import snakeandfood_v1.movementStrategy.MovementStrategy;

import java.util.Scanner;

public class SnakeAndFoodGame implements BoardGame {
    private Board board;
    private Snake snake;
    private Cell[] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;
    private Scanner inputScanner;
    private GameStatus gameStatus;

    public SnakeAndFoodGame(int boardHeight, int boardWidth, int[][] foodPosition, MovementStrategy movementStrategy) {
        board = Board.getBoardInstance(boardHeight, boardWidth);
        snake = new Snake();
        food = getFoodPosition(foodPosition);
        foodIndex = 0;
        this.movementStrategy = movementStrategy;
        inputScanner = new Scanner(System.in);
        gameStatus = GameStatus.IN_PROGRESS;
    }

    private Cell[] getFoodPosition(int[][] foodPosition) {
        Cell[] foodPos = new Cell[foodPosition.length];
        int i = 0;
        for (int j=0; j<foodPosition.length; j++) {
            foodPos[i] = new Cell(foodPosition[j][0], foodPosition[j][1]);
        }
        return foodPos;
    }

    @Override
    public void startGame() {
        while (gameStatus == GameStatus.IN_PROGRESS) {
            displayGameState(this);
            Directions direction = getMoveDirection();

            if (direction == null) {
                gameStatus = GameStatus.QUIT;
            }
            gameStatus = move(direction);
        }

        displayGameOverState(gameStatus);
        inputScanner.close();
        Board.resetInstance();
    }

    private void displayGameOverState(GameStatus gameStatus) {
        switch (gameStatus) {
            case SNAKE_BITE_ITSELF -> System.out.println("Snake bite itself! GAME OVER");
            case SNAKE_COLLIDE_WITH_WALL -> System.out.println("Snake collided with the wall! GAME OVER");
            case SAVED -> System.out.println("GAME SAVED!");
            case QUIT -> System.out.println("Game Quited!");
        }
        System.out.println("Final Score:" + this.snake.getBody().size());
    }

    private void displayGameState(SnakeAndFoodGame snakeAndFoodGame) {
        //Render on screen
        System.out.println("Current Score: " + snakeAndFoodGame.snake.getBody().size());
    }

    private GameStatus move(Directions direction) {
        // Current snake head
        Cell snakeHead = snake.getBody().getFirst();
        // Current snake tail
        Cell snakeTail = snake.getBody().getLast();
        // New snake head
        Cell newHead = movementStrategy.getNextPosition(snakeHead, direction);

        int newHeadRow = newHead.getRow();
        int newHeadCol = newHead.getCol();

        // Check if snake has collided with the boundary
        boolean snakeCrossedBorder = newHeadRow<0 || newHeadRow>= board.getHeight()
                || newHeadCol<0 || newHeadCol>= board.getWidth();
        if (snakeCrossedBorder) {
            return GameStatus.SNAKE_COLLIDE_WITH_WALL;
        }

        // Check if snake bites itself (excluding tail which will move away)
        boolean snakeBiteItself = snake.getPositionMap().containsKey(newHead) &&
                !(newHeadRow == snakeTail.getRow() && newHeadCol == snakeTail.getCol());
        if (snakeBiteItself) {
            return GameStatus.SNAKE_BITE_ITSELF;
        }


        // Chek if snake eaten food
        boolean ateFood = foodIndex < food.length &&
                (food[foodIndex].getRow()==newHeadRow && food[foodIndex].getCol()==newHeadCol);

        if (ateFood) {
            // Increment food index to move to next food
            foodIndex++;
        } else {
            // If no food eaten, remove tail
            snake.getBody().pollLast();
            snake.getPositionMap().remove(snakeTail);
        }

        // Add new head
        snake.getBody().addFirst(newHead);
        snake.getPositionMap().put(newHead, true);
        return GameStatus.IN_PROGRESS;
    }

    private Directions getMoveDirection() {
        System.out.println("Enter direction to move for snake: W/S/A/D or Q for QUIT");
        String dir = inputScanner.nextLine();
        return switch (dir) {
            case "W" -> Directions.UP;
            case "S" -> Directions.DOWN;
            case "A" -> Directions.LEFT;
            case "D" -> Directions.RIGHT;
            default -> null;
        };
    }
}
