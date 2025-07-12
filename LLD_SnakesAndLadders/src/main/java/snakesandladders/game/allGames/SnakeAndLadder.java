package snakesandladders.game.allGames;


import snakesandladders.Utils.Board;
import snakesandladders.Utils.Cell;
import snakesandladders.Utils.Dice;
import snakesandladders.Utils.CommonUtils;
import snakesandladders.enums.Status;
import snakesandladders.game.BoardGames;
import snakesandladders.obstacleFactory.Obstacle;
import snakesandladders.obstacleFactory.obstacleTypes.Ladder;
import snakesandladders.obstacleFactory.obstacleTypes.Snake;
import snakesandladders.playerFactory.Player;
import snakesandladders.playerFactory.playerTypes.Human;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadder implements BoardGames {
    Board gameBoard;
    Deque<Player> allPlayers;
    int noOfSnakes;
    int noOfLadders;
    Dice dice;
    Scanner inputScanner;
    Status gameStatus;

    public SnakeAndLadder(int row, int col, int noOfSnakes, int noOfLadders, int diceCount) {
        // Input validation
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Board dimensions must be positive");
        }
        if (noOfSnakes < 0) {
            throw new IllegalArgumentException("Number of snakes cannot be negative");
        }
        if (noOfLadders < 0) {
            throw new IllegalArgumentException("Number of ladders cannot be negative");
        }
        if (diceCount <= 0) {
            throw new IllegalArgumentException("Dice count must be positive");
        }
        
        int totalCells = row * col;
        if (noOfSnakes + noOfLadders >= totalCells - 1) { // -1 for starting position
            throw new IllegalArgumentException("Too many obstacles for board size");
        }
        
        this.gameBoard = new Board(row, col);
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;
        this.dice = new Dice(diceCount);
        this.inputScanner = new Scanner(System.in);
        this.gameStatus = Status.START;
        this.allPlayers = new LinkedList<>();
        initializePlayers();
        placeSnakes(row, col, noOfSnakes);
        placeLadders(row, col, noOfLadders);
    }

    private void placeLadders(int row, int col, int noOfLadders) {
        Random random = new Random();

        int ladderCount = 0;
        int attempts = 0;
        int maxAttempts = noOfLadders * 100; // Prevent infinite loops
        
        while (ladderCount < noOfLadders && attempts < maxAttempts) {
            attempts++;
            
            // Ladders: go from higher position numbers (lower on board) to lower position numbers (higher on board)
            int sourceRow = random.nextInt(1, row); // Start from rows 1 to row-1 (avoiding top row)
            int sourceCol = random.nextInt(0, col);
            Cell sourceCell = this.gameBoard.getBoard()[sourceRow][sourceCol];

            // Skip if cell already has obstacle or is starting/ending position
            if (sourceCell.isHasObstacle() || sourceCell.getPosition() == 1 || 
                sourceCell.getPosition() == row * col) {
                continue;
            }

            int destinationRow = random.nextInt(0, sourceRow); // Go to higher rows (0 to sourceRow-1)
            int destinationCol = random.nextInt(0, col);
            Cell destinationCell = this.gameBoard.getBoard()[destinationRow][destinationCol];

            // For ladders: destination should have HIGHER position number than source (advancing toward finish)
            if (destinationCell.getPosition() <= sourceCell.getPosition()) {
                continue;
            }

            Obstacle ladderObstacle = new Ladder(sourceCell, destinationCell);
            sourceCell.setHasObstacle(true);
            sourceCell.setObstacle(ladderObstacle);
            ladderCount++;
        }
        
        if (ladderCount < noOfLadders) {
            System.out.println("Warning: Could only place " + ladderCount + " out of " + noOfLadders + " ladders due to space constraints.");
        }
    }

    private void placeSnakes(int row, int col, int noOfSnakes) {
        Random random = new Random();

        int snakeCount = 0;
        int attempts = 0;
        int maxAttempts = noOfSnakes * 100; // Prevent infinite loops
        
        while (snakeCount < noOfSnakes && attempts < maxAttempts) {
            attempts++;
            
            // Snakes: go from lower position numbers (higher on board) to higher position numbers (lower on board)
            int sourceRow = random.nextInt(0, row - 1); // Start from rows 0 to row-2 (avoiding bottom row)
            int sourceCol = random.nextInt(0, col);
            Cell sourceCell = this.gameBoard.getBoard()[sourceRow][sourceCol];

            // Skip if cell already has obstacle or is starting/ending position
            if (sourceCell.isHasObstacle() || sourceCell.getPosition() == 1 || 
                sourceCell.getPosition() == row * col) {
                continue;
            }

            int destinationRow = random.nextInt(sourceRow + 1, row); // Go to lower rows (sourceRow+1 to row-1)
            int destinationCol = random.nextInt(0, col);
            Cell destinationCell = this.gameBoard.getBoard()[destinationRow][destinationCol];

            // For snakes: destination should have LOWER position number than source (going backward from finish)
            if (destinationCell.getPosition() >= sourceCell.getPosition()) {
                continue;
            }

            Obstacle snakeObstacle = new Snake(sourceCell, destinationCell);
            sourceCell.setHasObstacle(true);
            sourceCell.setObstacle(snakeObstacle);
            snakeCount++;
        }
        
        if (snakeCount < noOfSnakes) {
            System.out.println("Warning: Could only place " + snakeCount + " out of " + noOfSnakes + " snakes due to space constraints.");
        }
    }

    private void initializePlayers() {
        System.out.println("Enter choice: \n 1) All players are human \n 2) We have both humans and AI players in the game");
        // TODO: complete this function for user input handling
        int[] rowAndCol = CommonUtils.getRowAndColViaPosition(1, gameBoard.getRow(), gameBoard.getCol());
        allPlayers.add(new Human("Amit", new Cell(rowAndCol[0], rowAndCol[1], 1)));
        allPlayers.add(new Human("Pooja", new Cell(rowAndCol[0], rowAndCol[1], 1)));
    }

    @Override
    public void play() {
        this.gameStatus = Status.IN_PROGRESS;
        while (gameStatus == Status.IN_PROGRESS) {
            this.gameBoard.print();
            Player currPlayerTurn = this.allPlayers.removeFirst();
            
            int diceCount = currPlayerTurn.rollDice(this.dice);
            int consecutiveSixes = 0;
            
            // Count consecutive sixes and handle additional rolls
            while (diceCount == 6 && consecutiveSixes < 2) {
                System.out.println(currPlayerTurn.getName() + " rolled a 6! Rolling again...");
                consecutiveSixes++;
                int nextRoll = currPlayerTurn.rollDice(this.dice);
                diceCount += nextRoll;
                
                // If the next roll is not 6, break out of the loop
                if (nextRoll != 6) {
                    break;
                }
            }
            
            // Check if player got 3 consecutive sixes (total dice count divisible by 6 with consecutiveSixes >= 2)
            if (consecutiveSixes >= 2 && (diceCount % 6 == 0 || (diceCount - 6) % 6 == 0)) {
                System.out.println(currPlayerTurn.getName() + ", you got 6 three times in a row, turn skipped!");
                this.allPlayers.addLast(currPlayerTurn);
                continue;
            }

            System.out.println(currPlayerTurn.getName() + " rolled total: " + diceCount);
            
            Cell currCell = currPlayerTurn.getPosition();
            
            // Add bounds checking for current position
            if (currCell == null || currCell.getPosition() < 1 || currCell.getPosition() > this.gameBoard.getRow() * this.gameBoard.getCol()) {
                System.out.println("Invalid current position for " + currPlayerTurn.getName() + ". Resetting to start.");
                int[] startRowAndCol = CommonUtils.getRowAndColViaPosition(1, gameBoard.getRow(), gameBoard.getCol());
                currCell = new Cell(startRowAndCol[0], startRowAndCol[1], 1);
                currPlayerTurn.setPosition(currCell);
            }
            
            Cell destinationCell = gameBoard.move(currCell, diceCount);

            // Check for invalid moves (bounds checking)
            if (destinationCell.getPosition() > this.gameBoard.getRow() * this.gameBoard.getCol()) {
                System.out.println("Move exceeds board limit. " + currPlayerTurn.getName() + " stays at current position.");
                this.allPlayers.addLast(currPlayerTurn);
                continue;
            }
            
            // Additional check for negative positions (shouldn't happen but safety check)
            if (destinationCell.getPosition() < 1) {
                System.out.println("Invalid move calculation. " + currPlayerTurn.getName() + " stays at current position.");
                this.allPlayers.addLast(currPlayerTurn);
                continue;
            }

            if (destinationCell.isHasObstacle()) {
                Obstacle obstacle = destinationCell.getObstacle();
                if (obstacle != null && obstacle.getDestination() != null) {
                    System.out.println("We got a " + obstacle.getObstacleType() + " at " + destinationCell.getPosition());
                    destinationCell = obstacle.getDestination();
                    System.out.println("Because of " + obstacle.getObstacleType() + ", new position of " + currPlayerTurn.getName() + " is: " + destinationCell.getPosition());
                } else {
                    System.out.println("Warning: Invalid obstacle found at " + destinationCell.getPosition() + ". Ignoring obstacle.");
                }
            }

            currPlayerTurn.setPosition(destinationCell);
            System.out.println(currPlayerTurn.getName() + " is now at position: " + destinationCell.getPosition());

            if (destinationCell.getPosition() == this.gameBoard.getRow() * this.gameBoard.getCol()) {
                System.out.println(currPlayerTurn.getName() + " won the game!");
                this.gameStatus = Status.OVER;
                break;
            }
            
            this.allPlayers.addLast(currPlayerTurn);
        }
    }
}
