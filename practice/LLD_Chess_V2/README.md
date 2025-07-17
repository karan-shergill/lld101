# Chess V2 (Practice Implementation)

## LLD Requirements Fulfilled

### 1. **Scalability & Extensibility**
- ✅ **Multiple Game Support**: `Games` interface allows adding different board games
- ✅ **Multiple Player Types**: Factory pattern supports Human and AI players
- ✅ **Extensible Piece System**: Strategy pattern allows adding new piece types with custom movement rules
- ✅ **Modular Movement Logic**: Each piece type has its own movement strategy implementation
- ✅ **Clean Architecture**: Organized package structure with clear responsibilities

### 2. **Core Chess Architecture**
- ✅ **Standard Chess Setup**: Proper initial board configuration with all pieces
- ✅ **Strategy Pattern Implementation**: Individual movement strategies for each piece type
- ✅ **Turn-Based Gameplay**: Deque-based player alternation system
- ✅ **Move Validation Framework**: Foundation for piece-specific move validation
- ✅ **Factory-Based Creation**: Centralized piece and player creation
- ✅ **Abstract Class Hierarchy**: Clean inheritance structure for pieces and players

### 3. **Practice-Focused Design**
- ✅ **Simplified Input**: Hardcoded coordinates for focused learning
- ✅ **Clear Pattern Implementation**: Emphasis on design patterns over complete features
- ✅ **Extensible Foundation**: Well-structured base for adding complexity
- ✅ **Learning-Oriented**: Code structure optimized for understanding concepts

### 4. **System Architecture & Quality**
- ✅ **Singleton Pattern**: Single board instance management
- ✅ **Strategy Pattern**: Movement behavior abstraction
- ✅ **Factory Pattern**: Object creation abstraction
- ✅ **Clean Separation**: Logic separated by responsibility
- ⚠️ **Implementation Gaps**: Some factory methods need completion (noted for practice)

## Design Patterns Used

### 1. **Strategy Pattern**
- **Implementation**: `PiecePlayStrategy` interface with piece-specific strategies
- **Purpose**: Different movement behaviors for each chess piece
- **Benefit**: Easy to add new pieces or modify movement rules without changing existing code

```java
public interface PiecePlayStrategy {
    boolean isValidMove(Cell from, Cell to);
}

// Piece-specific implementations
class PawnStrategyPiece implements PiecePlayStrategy { /* Pawn logic */ }
class RookStrategyPiece implements PiecePlayStrategy { /* Rook logic */ }
class KingStrategyPiece implements PiecePlayStrategy { /* King logic */ }
```

### 2. **Factory Pattern**
- **Implementation**: `PieceFactory` and `PlayerFactory`
- **Purpose**: Creates objects without exposing instantiation logic
- **Benefit**: Easy to add new piece types and player types
- **Practice Note**: PieceFactory needs completion for different piece types

```java
Piece pawn = PieceFactory.getPieceOfType(PieceType.PAWN, true);
Player human = PlayerFactory.getPlayerOfType(PlayerType.HUMAN, true, "Alice");
Player ai = PlayerFactory.getPlayerOfType(PlayerType.AI, false, "Bot");
```

### 3. **Singleton Pattern**
- **Implementation**: `Board.getBoardInstance()`
- **Purpose**: Ensures only one game board exists per game session
- **Benefit**: Prevents multiple board instances and ensures consistent game state

### 4. **Template Method Pattern**
- **Implementation**: `Games` interface
- **Purpose**: Defines common structure for all board games
- **Benefit**: Standardized game flow for future game additions

### 5. **Abstract Factory Pattern**
- **Implementation**: Abstract `Piece` and `Player` classes
- **Purpose**: Provides framework for creating families of related objects
- **Benefit**: Consistent object creation with extensible implementations

## OOP Principles Followed

### 1. **Encapsulation**
- **Private Fields**: Board state, piece positions, player information, game status
- **Public Methods**: Controlled access through well-defined interfaces
- **Data Hiding**: Internal movement calculations and board management hidden

### 2. **Inheritance**
- **Base Classes**: Abstract `Piece` and `Player` classes
- **Derived Classes**: Specific pieces (Pawn, Rook, King, etc.) and player types (Human, AI)
- **Code Reuse**: Common attributes and methods in base classes

### 3. **Polymorphism**
- **Runtime Polymorphism**: `PiecePlayStrategy.isValidMove()` behaves differently for each piece
- **Interface Polymorphism**: `Games` interface allows uniform game treatment
- **Method Overriding**: Piece-specific movement implementations and player behaviors

### 4. **Abstraction**
- **Abstract Classes**: `Piece` and `Player` define contracts without full implementation
- **Interfaces**: `PiecePlayStrategy` and `Games` abstract behavior
- **Enums**: Abstract game constants (PieceType, PlayerType, GameStatus)

## Data Structures & Algorithms

### Data Structures Used

1. **2D Array** (`Cell[][]`)
   - **Purpose**: Represents the 8×8 chess board
   - **Time Complexity**: O(1) for access, O(64) for full board operations
   - **Why Used**: Natural representation of grid-based chess board

2. **Deque** (`LinkedList<Player>`)
   - **Purpose**: Manages turn-based player rotation
   - **Operations**: `removeFirst()`, `addLast()` for fair turn cycling
   - **Why Used**: Efficient insertion/removal from both ends for turn management

3. **Custom Objects**
   - **Cell**: Encapsulates board position with piece information
   - **Move**: Represents chess moves with source/destination and piece
   - **Piece**: Represents chess pieces with type, color, and strategy

4. **Enums**
   - **PieceType**: Type-safe piece classification (PAWN, ROOK, KING, etc.)
   - **PlayerType**: Player classification (HUMAN, AI)
   - **GameStatus**: Game state management (START, IN_PROGRESS, END)

### Algorithms Used

1. **Chess Board Initialization Algorithm**
   - **Time Complexity**: O(1) (fixed 64 cells)
   - **Logic**: Standard chess setup with factory pattern
   ```java
   // Black pieces: row 0 (back rank), row 1 (pawns)
   // White pieces: row 7 (back rank), row 6 (pawns)
   // Empty cells: rows 2-5
   board[0][0] = new Cell(0, 0, PieceFactory.getPieceOfType(PieceType.ROOK, false));
   ```

2. **Move Validation Framework**
   - **Time Complexity**: O(1) for strategy-based validation
   - **Logic**: Strategy pattern delegation for piece-specific rules
   ```java
   boolean isValid = move.getPiece().getPiecePlayStrategy().isValidMove(from, to);
   ```

3. **Turn Management Algorithm**
   - **Time Complexity**: O(1)
   - **Logic**: Deque-based player rotation with error handling
   ```java
   Player currPlayer = players.removeFirst();
   // Process move
   players.addLast(currPlayer); // or addFirst() for retry
   ```

4. **Position Validation Algorithm**
   - **Time Complexity**: O(1)
   - **Logic**: Multi-layered validation process
   ```java
   boolean validFrom = board.isValidPiecePositionFrom(row1, col1, isWhite);
   boolean validTo = board.isValidPiecePositionTo(row2, col2, isWhite);
   ```

5. **Win Detection Algorithm** (Foundation)
   - **Time Complexity**: O(1)
   - **Logic**: King capture detection
   ```java
   if (move.getTo().getPiece().getChessPiece() == PieceType.KING) {
       // Current player wins
   }
   ```

### Key Algorithms to Know for LLD Problems

1. **Strategy Pattern Implementation**: Pluggable algorithm architectures
2. **2D Grid Management**: Coordinate-based game board operations
3. **State Machine Implementation**: Game status management and transitions
4. **Factory Method Patterns**: Type-based object creation systems
5. **Move Validation**: Multi-criteria validation with business rules
6. **Turn-based Game Logic**: Player alternation and state consistency
7. **Object-Oriented Game Design**: Clean architecture for complex game systems

## Technical Specifications

### Chess Piece Strategy Framework
- **Pawn Strategy**: Forward movement with diagonal capture logic
- **Rook Strategy**: Horizontal and vertical line movement
- **Bishop Strategy**: Diagonal line movement
- **Knight Strategy**: L-shaped move pattern
- **Queen Strategy**: Combination of rook and bishop movements
- **King Strategy**: One square in any direction with castling support

### Game Flow Architecture
- **Initialization**: Board setup with factory-generated pieces and players
- **Turn Management**: Deque-based player alternation
- **Move Processing**: Validation → Strategy Check → Execution → State Update
- **Win Detection**: King capture triggers immediate game termination
- **Error Handling**: Comprehensive validation at multiple levels

### Board Management System
- **Coordinate System**: 8×8 grid with (0,0) at top-left
- **Piece Placement**: Standard chess configuration
- **Position Validation**: Boundary checking and piece ownership verification
- **State Updates**: Immediate board state reflection for all changes

### Practice-Oriented Features
- **Simplified Input**: Hardcoded coordinates for focused learning
- **Pattern Emphasis**: Clear implementation of design patterns
- **Extensible Structure**: Foundation for adding complete chess rules
- **Learning Focus**: Code optimized for understanding over completeness

## Technical Learning Outcomes

- **Design Patterns**: Strategy, Factory, Singleton, Template Method, Abstract Factory
- **OOP Concepts**: Inheritance, Polymorphism, Encapsulation, Abstraction
- **Data Structures**: 2D Arrays, Deque, Custom Objects, Enums
- **Algorithms**: Grid navigation, strategy pattern implementation, state management
- **Game Programming**: Turn management, rule enforcement, state consistency
- **Architecture Design**: Clean separation of concerns, extensible frameworks
- **Best Practices**: Interface-driven design, factory-based creation, strategic abstraction

## Practice Focus Areas

### 1. **Design Pattern Mastery**
- Implementing Strategy pattern for piece movement
- Understanding Factory pattern for object creation
- Singleton pattern for resource management
- Template method for standardized game flow

### 2. **Algorithm Development**
- Complete piece movement strategy implementations
- Enhanced move validation algorithms
- Win condition detection and game state management

### 3. **Architecture Understanding**
- Clean separation between game logic and presentation
- Extensible design for future feature additions
- Error handling and input validation patterns

## Areas for Enhancement

### 1. **Complete Implementation**
- **Fix PieceFactory**: Implement proper piece type creation
- **Strategy Completion**: Implement all piece movement strategies
- **Input System**: Add real user input handling
- **Win Conditions**: Complete win/draw detection logic

### 2. **Advanced Chess Features**
- **Special Moves**: Castling, en passant, pawn promotion
- **Check Detection**: King safety validation
- **Game Rules**: Stalemate, checkmate, fifty-move rule
- **Move History**: Complete game replay capability

### 3. **Code Quality Improvements**
- **Error Handling**: Comprehensive exception management
- **Input Validation**: Robust user input processing
- **Unit Testing**: Test coverage for all components
- **Documentation**: Method-level documentation

### 4. **Advanced Features**
- **AI Enhancement**: Minimax algorithm implementation
- **Save/Load**: Game state persistence
- **Network Play**: Multi-player support
- **Chess Notation**: PGN format support
