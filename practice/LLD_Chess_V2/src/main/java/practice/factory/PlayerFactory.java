package practice.factory;

import practice.constants.PlayerType;
import practice.modal.Player;
import practice.playerType.AI;
import practice.playerType.Human;

public class PlayerFactory {
    public static Player getPlayerOfType(PlayerType playerType, boolean whitePiece, String name) {
        switch (playerType) {
            case HUMAN -> {
                return new Human(name, whitePiece);
            }
            case AI -> {
                return new AI(name, whitePiece);
            }
        }
        return null;
    }
}
