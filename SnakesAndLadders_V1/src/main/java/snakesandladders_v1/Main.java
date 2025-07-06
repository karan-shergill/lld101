package snakesandladders_v1;

import snakesandladders_v1.game.BoardGames;
import snakesandladders_v1.game.allGames.SnakeAndLadder;

public class Main {
    public static void main(String[] args) {
        BoardGames snakeAndLadder = new SnakeAndLadder(4, 5, 3, 4, 1);
        snakeAndLadder.play();
    }
}