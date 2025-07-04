package Chess_V1;

import Chess_V1.game.BoardGames;
import Chess_V1.game.allGames.ChessGame;
import Chess_V1.playerFactory.Player;
import Chess_V1.playerFactory.PlayerFactory;
import Chess_V1.playerFactory.PlayerType;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to BOARD games!");
        System.out.println("Want to play with another human or AI? \n 1> Play with HUMAN \n 2> Play with AI");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Player player1;
        Player player2;
        if (choice == 1) {
            player1 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN, "PLAYER_1", true);
            player2 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN, "PLAYER_2", false);
        } else {
            player1 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN, "PLAYER_1", true);
            player2 = PlayerFactory.getPlayerOfType(PlayerType.AI, "PLAYER_2", false);
        }
        BoardGames chess = new ChessGame(player1, player2);
        chess.startGame();
        scanner.close();
    }
}