package tictactoe.factory;

import tictactoe.constants.PlayingPiece;
import tictactoe.constants.PlayerType;
import tictactoe.model.Player;
import tictactoe.players.AIPlayer;
import tictactoe.players.HumanPlayer;

public class PlayerFactory {
    public static Player createPlayer(PlayerType playerType, String playerName, PlayingPiece playingPiece) {
        switch (playerType) {
            case HUMAN -> {
                return new HumanPlayer(playerName, playingPiece);
                }
            case AI -> {
                return new AIPlayer(playerName, playingPiece);
            }
        }
        return null;
    }
}