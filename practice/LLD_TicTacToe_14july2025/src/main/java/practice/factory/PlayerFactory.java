package practice.factory;

import practice.constants.PlayerType;
import practice.constants.PlayingSymbol;
import practice.models.Player;
import practice.player.AI;
import practice.player.Human;

public class PlayerFactory {
    public static Player getPlayerOfType(PlayerType playerType, String name, PlayingSymbol playingSymbol) {
        switch (playerType) {
            case HUMAN -> {
                return new Human(name, playingSymbol);
            }
            case AI -> {
                return new AI(name, playingSymbol);
            }
        }
        return null;
    }
}
