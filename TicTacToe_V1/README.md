# Tic Tac Toe V1

- No design patterns been used in this version
- No use of interface of any major OOPs principle like SOLID


## Learning

- Use Deque in case of game LLD where player turn will come in a circle
- Learning to break code into smaller models - classes
- Scanner to take input for user's move and split in an array

## Feedback from LLM models

- Violation of Single Responsibility Principle - TicTacToeGame handles game logic, user input, initialization, and display
  Should be split into separate classes (GameController, InputHandler, GameInitializer)
- Poor Input Validation
- Design Pattern Opportunities: Strategy Pattern, Factory Pattern, Observer Pattern, Command Pattern
- Multiple Scanner instances created but never closed, should use try-with-resources or single Scanner instance
- playTicTacToeGame() method is too long and does too much
- checkIfCurrPlayerIsWinner() has repeated logic

## Interview Assessment:

Positive Points:
- Shows understanding of OOP concepts
- Good use of data structures (Deque)
- Clean code structure with proper packages

Areas of Concern:

- Critical bugs that would fail in production
- Missing error handling
- Violation of SOLID principles

Overall Rating: 6/10

- Functional but has critical bugs
- Shows potential but needs significant improvements
- Good foundation but lacks production-ready quality

Recommendation: Focus on fixing the critical bugs first, then work on design patterns and error handling. The core logic is sound, but attention to detail and edge cases needs improvement.