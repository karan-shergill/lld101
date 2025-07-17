package practice;

import practice.constants.PlayerType;
import practice.factory.PlayerFactory;
import practice.modal.Player;
import practice.service.ChessGame;
import practice.service.Games;

import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Player player1 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN,true , "Amit");
        Player player2 = PlayerFactory.getPlayerOfType(PlayerType.HUMAN,false , "Pooja");
        Deque<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        Games chess = new ChessGame(players);
        chess.play();
    }
}