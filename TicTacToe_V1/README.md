# Tic Tac Toe V1

- No design patterns been used in this version
- No use of interface of any major OOPs principle like SOLID


# Learning

- Use Deque in case of game LLD where player turn will come in a circle
- Learning to break code into smaller models - classes
- Scanner to take input for user's move and split in an array

# Feedback from LLM models

Based on my analysis of your Tic Tac Toe LLD, here's a comprehensive review:

## ✅ **Strengths of the Current Design**

### 1. **Good Separation of Concerns**
- Clean separation between game logic (`TicTacToeGame`) and data models (`model` package)
- Each class has a single, well-defined responsibility
- Model classes are properly encapsulated with private fields and public getters

### 2. **Immutable Design**
- All model classes use `final` fields, making them immutable
- This prevents accidental state changes and makes the code more predictable

### 3. **Flexible Board Size**
- The design supports variable board sizes (NxN), not just 3x3
- This makes the game more extensible

### 4. **Proper Use of Enums**
- `PlayingPieceType` enum provides type safety for piece types
- Better than using raw strings or integers

### 5. **Clean Model Structure**
- `Player` → `PlayingPiece` → `PlayingPieceType` hierarchy is logical
- Clear ownership and relationships between entities

## ⚠️ **Areas for Improvement**

### 1. **Input Validation & Error Handling**
```java
// Current code lacks robust input validation
int row = Integer.parseInt(playerInput[0]); // Can throw NumberFormatException
```

**Recommendation:** Add proper validation and exception handling for user inputs.

### 2. **Resource Management**
```java
// Scanner is created multiple times but not properly closed
Scanner inputScanner = new Scanner(System.in);
```

**Recommendation:** Use try-with-resources or create a single Scanner instance.

### 3. **Mixed Responsibilities**
The `TicTacToeGame` class handles both:
- Game logic (winner checking, move validation)
- User I/O (reading input, printing messages)

**Recommendation:** Separate I/O concerns into a dedicated class (e.g., `GameInterface` or `ConsoleUI`).

### 4. **Complex Winner Checking Logic**
```java
// Current winner checking is verbose and checks entire lines
private boolean checkIfCurrPlayerIsWinner(PlayingPiece playingPiece, int row, int col)
```

**Recommendation:** The logic could be optimized to only check the affected row, column, and diagonals.

### 5. **Hard-coded Two-Player Logic**
```java
// Logic assumes exactly 2 players
Player player2 = new Player(input,
    player1Piece.equals("X") ? new PlayingPiece(PlayingPieceType.O) : new PlayingPiece(PlayingPieceType.X));
```

**Recommendation:** Make the number of players configurable.

### 6. **Missing Interfaces/Abstractions**
No interfaces are defined, making it harder to:
- Test the code with mocks
- Extend with different board implementations
- Support different game variants

### 7. **Board State Exposure**
```java
public PlayingPiece[][] getBoard() {
    return board; // Exposes internal array reference
}
```

**Recommendation:** Return a defensive copy or provide specific query methods.

## 🔄 **Suggested Improvements**

### 1. **Extract I/O Interface**
```java
public interface GameInterface {
    void displayMessage(String message);
    void displayBoard(PlayingBoard board);
    String getUserInput(String prompt);
    void close();
}
```

### 2. **Add Input Validation**
```java
public class InputValidator {
    public static boolean isValidMove(String input) {
        // Validate input format and range
    }
}
```

### 3. **Optimize Winner Checking**
```java
private boolean checkWinner(PlayingPiece piece, int row, int col) {
    return checkRow(piece, row) || 
           checkColumn(piece, col) || 
           checkDiagonals(piece, row, col);
}
```

### 4. **Add Game Configuration**
```java
public class GameConfig {
    private final int boardSize;
    private final List<Player> players;
    // Configuration options
}
```

## 📊 **Design Pattern Opportunities**

1. **Strategy Pattern**: For different winning conditions
2. **Observer Pattern**: For game state notifications
3. **Factory Pattern**: For creating different game variants
4. **Command Pattern**: For move history/undo functionality

## 🎯 **Overall Assessment**

**Rating: 7/10**

**Strengths:**
- Solid foundation with good OOP principles
- Clean, readable code structure
- Proper encapsulation and immutability
- Extensible board size

**Key Improvements Needed:**
- Better separation of concerns (I/O vs game logic)
- Robust input validation and error handling
- Resource management
- Interface abstractions for testability

The design demonstrates good understanding of OOP principles and provides a solid foundation. With the suggested improvements, it would be production-ready and highly maintainable.