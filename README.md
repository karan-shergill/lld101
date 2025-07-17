# Low-Level Design Use Cases

Prerequisite: [Object-Oriented Programming & Design Patterns](https://github.com/karan-shergill/oops101)

## Use Cases Overview

| Use Case | Description | Implementation Status | Practice Attempts |
|----------|-------------|---------------------|------------------|
| Tic Tac Toe | Classic 3x3 grid game | Completed | 2 |
| Snake And Food | Snake game with different food types | Completed | 2 |
| Chess | Complete chess game implementation | Completed | 2 |
| Parking Lot | Multi-level parking management system | Completed | 1 |
| Snakes And Ladders | Board game with obstacles | Completed | 1 |

## Detailed Implementation

### Tic Tac Toe Game

| Aspect | Details |
|--------|---------|
| **Code Implementations** | [Practice_1](https://github.com/karan-shergill/lld101/tree/main/LLD_TicTacToe_V1), [Practice_2](https://github.com/karan-shergill/lld101/tree/main/practice/LLD_TicTacToe_V2) |
| **Design Patterns** | Singleton (game board), Factory (player type), Strategy (AI moves) |
| **Key Algorithms** | Win check after move, Tie check, Player turn management |
| **Core Components** | GameBoard, Player, PlayingPiece, AI/Human players |

### Snake And Food Game

| Aspect | Details |
|--------|---------|
| **Code Implementations** | [Practice_1](https://github.com/karan-shergill/lld101/tree/main/LLD_SnakeAndFood_V1), [Practice_2](https://github.com/karan-shergill/lld101/tree/main/practice/LLD_SnakeAndFood_V2) |
| **Design Patterns** | Singleton (game board), Factory (food type, player type), Strategy (AI moves) |
| **Key Algorithms** | Snake body management, Collision detection, Random food placement |
| **Core Components** | Snake, Food items (Normal/Super/Poisonous), Board, Movement strategies |

### Chess Game

| Aspect | Details |
|--------|---------|
| **Code Implementations** | [Practice_1](https://github.com/karan-shergill/lld101/tree/main/LLD_Chess_V1), [Practice_2](https://github.com/karan-shergill/lld101/tree/main/practice/LLD_Chess_V2) |
| **Design Patterns** | Factory (piece creation), Strategy (piece movement), Command (move execution) |
| **Key Algorithms** | Piece movement validation, Check/Checkmate detection, Castling logic |
| **Core Components** | Pieces (King, Queen, Rook, etc.), Board, Movement strategies, Game rules |

### Parking Lot System

| Aspect | Details |
|--------|---------|
| **Code Implementations** | [Practice_1](https://github.com/karan-shergill/lld101/tree/main/LLD_ParkingLot_V1) |
| **Design Patterns** | Factory (vehicle/spot creation), Strategy (parking/payment), Observer (display board) |
| **Key Algorithms** | Spot allocation, Rate calculation, Nearest parking logic |
| **Core Components** | ParkingLot, ParkingFloor, Spots, Vehicles, Payment system, Tickets |

### Snakes And Ladders

| Aspect | Details |
|--------|---------|
| **Code Implementations** | [Practice_1](https://github.com/karan-shergill/lld101/tree/main/LLD_SnakesAndLadders_V1) |
| **Design Patterns** | Factory (player/obstacle creation), Command (dice roll), Strategy (AI moves) |
| **Key Algorithms** | Board traversal, Obstacle handling, Win condition check |
| **Core Components** | Board, Players, Obstacles (Snakes/Ladders), Dice, Game controller |

## Design Patterns Summary

| Pattern | Use Cases | Purpose |
|---------|-----------|---------|
| **Singleton** | Tic Tac Toe, Snake And Food | Ensure single game board instance |
| **Factory** | All projects | Create objects (players, pieces, vehicles, spots) |
| **Strategy** | All projects | Implement different behaviors (AI moves, payment methods, parking strategies) |
| **Command** | Chess, Snakes And Ladders | Encapsulate actions (moves, dice rolls) |
| **Observer** | Parking Lot | Update display boards and notifications |
