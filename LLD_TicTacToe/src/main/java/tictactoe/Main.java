package tictactoe;

import tictactoe.service.TicTacToeGame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Board Game");
        // Future expansion: can choose the game the payer wants to play - Factory pattern

        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
        game.cleanup();
    }
}