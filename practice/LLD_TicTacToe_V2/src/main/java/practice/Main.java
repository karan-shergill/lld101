package practice;

import practice.constants.PlayerType;
import practice.constants.PlayingSymbol;
import practice.factory.PlayerFactory;
import practice.models.Board;
import practice.models.Player;
import practice.service.TicTacToeGame;

import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe Game!");

        // For practicing hardcoding data but in reality will take input via scanner
        Player player1 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN, "Amit", PlayingSymbol.X);
        Player player2 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN, "Pooja", PlayingSymbol.O);

        Deque<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);

        Board board = Board.getBoardInstance(3);

        TicTacToeGame ticTacToeGame = new TicTacToeGame(players, board);
        ticTacToeGame.play();
    }
}