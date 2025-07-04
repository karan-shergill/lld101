## Tips for LLD interviews

1. Factory and Strategy design patterns or one o them will 100% be used in any LLD problem. 
2. Taking input from user via Scanner
   1. Use a single Scanner instance for System.in throughout your application 
   2. Pass Scanner instances as parameters between methods 
   3. Close Scanner only when your application is completely finished with input
3. Game kind of LLD problem
   1. Use Dequeue in case of a game type design where turn of players comes one by one.
   2. Consider game as person vs person / person vs AI 
   3. Input from user can be in a infinite loop, with input validation till user provided a valid input in desired format.
   4. Singleton design pattern: can always use for single instance of any game 
   5. Factory design pattern: can always use for HUMAN or AI object initialization | Game piece initialization
   6. Strategy design pattern: diff game pieces(of same family) have diff strategy for movement
   7. Game status enum
   8. Board game interface for actual game for future extension
   9. Can have a move object to store all the moves played by the players in some data structure like list/array
4. All LLD design will have some kind of input taken from the user, make sure input is wrapped around try catch block, check and throw exception or log error in case of incorrect format.
5. All Enums in same module, all constants in a file(prohibit hardcoding in code)