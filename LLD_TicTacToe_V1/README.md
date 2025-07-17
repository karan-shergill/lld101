# Tic Tac Toe V1

## LLD Requirements Fulfilled

### 1. **Scalability & Extensibility**
- ✅ **Multiple Game Support**: `BoardGames` interface allows adding new board games (Chess, Checkers, etc.)
- ✅ **Multiple Player Types**: Easy to add new player types (Remote, Advanced AI, etc.) via `PlayerFactory`
- ✅ **Configurable Board Size**: Dynamic board size (3x3, 4x4, 5x5, etc.)
- ✅ **Multiple Playing Pieces**: Enum-based system allows adding new symbols beyond X and O

### 2. **Modularity & Separation of Concerns**
- ✅ **Game Logic Separation**: Core game logic separated from UI/input handling
- ✅ **Player Management**: Independent player creation and management
- ✅ **Board Management**: Dedicated board class with validation logic
- ✅ **Factory-based Object Creation**: Centralized object creation for players

### 3. **Game State Management**
- ✅ **State Tracking**: Comprehensive game status (START, IN_PROGRESS, PAUSE, TIE, END)
- ✅ **Turn Management**: Fair turn rotation using Deque data structure
- ✅ **Win Detection**: Efficient algorithm for rows, columns, and diagonals
- ✅ **Input Validation**: Robust validation for moves and user inputs

### 4. **Code Quality & Maintainability**
- ✅ **Single Responsibility Principle**: Each class has one clear purpose
- ✅ **Error Handling**: Comprehensive input validation and error recovery
- ✅ **Resource Management**: Proper cleanup of system resources (Scanner)
- ✅ **Thread Safety**: Synchronized singleton implementation

## Design Patterns Used

### 1. **Factory Pattern**
- **Implementation**: `PlayerFactory.createPlayer()`
- **Purpose**: Creates different types of players (Human, AI) without exposing instantiation logic
- **Benefit**: Easy to add new player types without modifying existing code

```java
Player humanPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN, name, piece);
Player aiPlayer = PlayerFactory.createPlayer(PlayerType.AI, name, piece);
```

### 2. **Singleton Pattern**
- **Implementation**: `GameBoard.getGameBoardInstance()`
- **Purpose**: Ensures only one game board instance exists per game
- **Benefit**: Prevents multiple board instances and ensures game state consistency
- **Thread Safety**: Synchronized method for concurrent access

### 3. **Template Method Pattern**
- **Implementation**: `BoardGames` interface
- **Purpose**: Defines common structure for all board games
- **Benefit**: Standardized game flow (initiate → play) for future game additions

### 4. **Strategy Pattern**
- **Implementation**: `Player.makeMove()` abstract method
- **Purpose**: Different move strategies for Human vs AI players
- **Benefit**: Easy to implement different AI algorithms or player behaviors

## OOP Principles Followed

### 1. **Encapsulation**
- **Private Fields**: Game board, player details, game status
- **Public Methods**: Controlled access through getter/setter methods
- **Data Hiding**: Internal implementation details hidden from clients

### 2. **Inheritance**
- **Base Class**: Abstract `Player` class
- **Derived Classes**: `HumanPlayer` and `AIPlayer`
- **Code Reuse**: Common player attributes and methods in base class

### 3. **Polymorphism**
- **Runtime Polymorphism**: `Player.makeMove()` behaves differently for Human vs AI
- **Interface Polymorphism**: `BoardGames` interface allows treating different games uniformly
- **Method Overriding**: Different implementations of `makeMove()` in subclasses

### 4. **Abstraction**
- **Abstract Classes**: `Player` class defines contract without implementation
- **Interfaces**: `BoardGames` interface abstracts game behavior
- **Enums**: Abstract game constants (GameStatus, PlayerType, PlayingPiece)

## Data Structures & Algorithms

### Data Structures Used

1. **2D Array** (`PlayingPiece[][]`)
   - **Purpose**: Represents game board matrix
   - **Time Complexity**: O(1) for access, O(n²) for traversal
   - **Why Used**: Natural representation of grid-based game

2. **Deque** (`LinkedList<Player>`)
   - **Purpose**: Manages turn-based player rotation
   - **Operations**: `removeFirst()`, `addLast()`, `addFirst()`
   - **Why Used**: Efficient insertion/removal from both ends for turn management

3. **Enums** 
   - **GameStatus**: Manages game state transitions
   - **PlayerType**: Type-safe player classification
   - **PlayingPiece**: Immutable game symbols

### Algorithms Used

1. **Win Detection Algorithm**
   - **Time Complexity**: O(n) where n is board size
   - **Space Complexity**: O(1)
   - **Logic**: Check row, column, diagonal, and anti-diagonal from last move
   ```java
   // Checks: horizontal, vertical, diagonal, anti-diagonal
   rowCheck || columnCheck || diagonalCheck || antiDiagonalCheck
   ```

2. **Move Validation Algorithm**
   - **Time Complexity**: O(1)
   - **Validation**: Bounds checking + empty cell verification
   ```java
   row >= 0 && row < size && col >= 0 && col < size && board[row][col] == null
   ```

3. **Empty Cell Detection**
   - **Time Complexity**: O(n²)
   - **Purpose**: Determine if game can continue
   - **Early Termination**: Returns true on first empty cell found

4. **AI Move Strategy** (Basic Implementation)
   - **Algorithm**: First-available-move strategy
   - **Time Complexity**: O(n²) worst case
   - **Extensibility**: Can be enhanced with minimax, alpha-beta pruning

### Key Algorithms to Know for LLD Problems

1. **Matrix Traversal**: Row, column, diagonal scanning
2. **Game State Management**: State machine implementation
3. **Input Validation**: Robust error handling patterns
4. **Turn-based Logic**: Queue/Deque operations for fair rotation
5. **Win Condition Checking**: Pattern matching in grids
6. **Factory Method**: Object creation patterns
7. **Singleton Implementation**: Thread-safe singleton patterns

## Technical Learning Outcomes

- **Design Patterns**: Factory, Singleton, Strategy, Template Method
- **OOP Concepts**: Inheritance, Polymorphism, Encapsulation, Abstraction
- **Data Structures**: 2D Arrays, Deque, Enums
- **Algorithms**: Matrix operations, state management, validation logic
- **Best Practices**: Input validation, resource management, error handling
- **Extensibility**: Interface-driven design for future enhancements 
