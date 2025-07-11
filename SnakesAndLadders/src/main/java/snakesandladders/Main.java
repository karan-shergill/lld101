package snakesandladders;

import snakesandladders.game.BoardGames;
import snakesandladders.game.allGames.SnakeAndLadder;

public class Main {
    public static void main(String[] args) {
        BoardGames snakeAndLadder = new SnakeAndLadder(4, 5, 3, 4, 1);
        snakeAndLadder.play();
    }
}