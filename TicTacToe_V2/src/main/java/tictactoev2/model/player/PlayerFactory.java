package tictactoev2.model.player;

import tictactoev2.model.PlayingPiece;

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