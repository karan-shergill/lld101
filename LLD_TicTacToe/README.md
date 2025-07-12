# Tic Tac Toe V1

## Learning
- Factory Design Pattern - creating object for players/AI player
- Interface for future expansion
- Input validation 

## What Can be improved
- TicTacToeGame class is very big and to much going on. This could had been broken into smaller classes.

## UML

![TicTacToe_V2_UML_2](/uml_images/TicTacToe_V2_UML_2.png)
![TicTacToe_V2_UML](/uml_images/TicTacToe_V2_UML.png)

# Feedback from LLM - LLD Interview Feedback: TicTacToe Game Implementation

## 🎯 **Overall Assessment**
Your implementation shows good understanding of OOP principles and design patterns. However, there are several areas that need improvement for a strong LLD interview performance, especially for a 7-year experienced developer.

## ✅ **Strengths**

### 1. **Good Design Patterns Usage**
- **Factory Pattern**: `PlayerFactory` for creating different player types
- **Strategy Pattern**: Abstract `Player` class with concrete implementations
- **Enum Usage**: Proper use of enums for `PlayingPiece` and `PlayerType`

### 2. **Clean Package Structure**
- Well-organized package hierarchy
- Separation of models and player types
- Future extensibility consideration with `BoardGames` interface

### 3. **Extensibility Mindset**
- Support for different board sizes
- Human vs AI player modes
- Comments indicating future extensions

## 🔴 **Critical Issues**

### 1. **Separation of Concerns Violation**
**Problem**: Game logic is tightly coupled with UI/console interaction in `TicTacToeGame`

```java
// BAD: Game logic mixed with UI
private int[] getInputForCurrPlayerTurn(Player currPlayer) {
    gameBoard.printGameBoard();  // UI in game logic
    System.out.println("..."); // Direct console interaction
}
```

**Solution**: Separate game logic from presentation layer
```java
// GOOD: Clean separation
public class TicTacToeGame {
    private GameEngine gameEngine;
    private GameView gameView;
    
    public void playGame() {
        while (!gameEngine.isGameOver()) {
            Move move = gameView.getPlayerMove(gameEngine.getCurrentPlayer());
            gameEngine.makeMove(move);
            gameView.displayBoard(gameEngine.getBoard());
        }
    }
}
```

### 2. **Broken Abstraction in AIPlayer**
**Problem**: `AIPlayer.makeMove()` ignores input parameters and implements its own logic

```java
// BAD: Ignores row, col parameters
public boolean makeMove(GameBoard gameBoard, int row, int col) {
    for (int i = 0; i < gameBoard.getSize(); i++) {
        for (int j = 0; j < gameBoard.getSize(); j++) {
            if (gameBoard.isValidMove(i, j)) {
                gameBoard.getBoard()[i][j] = playerPlayingPiece;
                return true;
            }
        }
    }
    return false;
}
```

**Solution**: Proper abstraction design
```java
public abstract class Player {
    public abstract Move getNextMove(GameBoard gameBoard);
    public void makeMove(GameBoard gameBoard, Move move) {
        gameBoard.placePiece(move.getRow(), move.getCol(), this.playerPlayingPiece);
    }
}
```

### 3. **Missing Core Abstractions**
**Problem**: No `Move` class, no `Game` state management

**Solution**: Introduce proper abstractions
```java
public class Move {
    private final int row;
    private final int col;
    private final PlayingPiece piece;
    
    // Constructor, getters, validation
}

public enum GameState {
    IN_PROGRESS, PLAYER_X_WINS, PLAYER_O_WINS, DRAW
}
```

## 🟡 **Design Improvements**

### 1. **Immutable Data Structures**
**Problem**: Exposed mutable board array
```java
public PlayingPiece[][] getBoard() {
    return board; // Exposes internal state
}
```

**Solution**: Provide read-only access
```java
public PlayingPiece getPiece(int row, int col) {
    return board[row][col];
}

public PlayingPiece[][] getBoardCopy() {
    // Return defensive copy
}
```

### 2. **Better Win Condition Logic**
**Problem**: Win checking logic is repeated and inefficient

**Solution**: Extract to separate class
```java
public class WinConditionChecker {
    public boolean isWinningMove(GameBoard board, int row, int col, PlayingPiece piece) {
        return checkRow(board, row, piece) || 
               checkColumn(board, col, piece) || 
               checkDiagonals(board, row, col, piece);
    }
}
```

### 3. **Input Validation Layer**
**Problem**: Input validation scattered throughout code

**Solution**: Centralized validation
```java
public class InputValidator {
    public static ValidationResult validateMove(String input, int boardSize) {
        // Comprehensive validation logic
    }
}
```

## 🔧 **Code Quality Issues**

### 1. **Resource Management**
**Problem**: Scanner resource leak potential
```java
// BAD: Scanner might not be closed in all scenarios
private Scanner inputScanner;
```

**Solution**: Use try-with-resources
```java
try (Scanner scanner = new Scanner(System.in)) {
    // Use scanner
}
```

### 2. **Magic Numbers and Constants**
**Problem**: Hard-coded values
```java
case 1 -> { getTwoPlayers(players); }
case 2 -> { getOnePlayer(players); }
```

**Solution**: Use constants
```java
private static final int HUMAN_VS_HUMAN = 1;
private static final int HUMAN_VS_AI = 2;
```

### 3. **Error Handling**
**Problem**: Limited error handling for edge cases

**Solution**: Proper exception handling
```java
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}
```

## 🚀 **Scalability & Extensibility**

### 1. **Configuration Management**
**Problem**: Hard-coded game settings

**Solution**: Configuration class
```java
public class GameConfiguration {
    private final int boardSize;
    private final int playersCount;
    private final List<PlayerType> playerTypes;
    
    // Builder pattern for configuration
}
```

### 2. **Game State Persistence**
**Problem**: No save/load functionality

**Solution**: Add persistence layer
```java
public interface GameStateRepository {
    void saveGame(GameState state);
    GameState loadGame(String gameId);
}
```

## 📝 **LLD Interview Best Practices**

### 1. **Start with Requirements**
- Always clarify requirements first
- Discuss extension points
- Identify non-functional requirements

### 2. **Design Before Code**
- Draw class diagrams
- Identify relationships
- Discuss trade-offs

### 3. **Follow SOLID Principles**
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Liskov Substitution**: Derived classes must be substitutable
- **Interface Segregation**: Clients shouldn't depend on unused interfaces
- **Dependency Inversion**: Depend on abstractions, not concretions

### 4. **Consider Testing**
- Design for testability
- Mock external dependencies
- Unit test individual components

## 🎯 **Recommended Refactoring Priority**

1. **High Priority**:
    - Separate UI from game logic
    - Fix AIPlayer abstraction
    - Add proper Move class

2. **Medium Priority**:
    - Improve error handling
    - Add input validation layer
    - Implement proper resource management

3. **Low Priority**:
    - Add configuration management
    - Implement save/load functionality
    - Add comprehensive logging

## 🏆 **Interview Performance Tips**

1. **Communicate Trade-offs**: Explain why you chose specific patterns
2. **Think Aloud**: Verbalize your design decisions
3. **Ask Questions**: Clarify ambiguous requirements
4. **Start Simple**: Begin with core functionality, then extend
5. **Handle Edge Cases**: Consider error scenarios and boundary conditions

Your implementation shows good foundation knowledge, but focus on clean abstractions, proper separation of concerns, and thinking about real-world scalability to excel in senior LLD interviews.