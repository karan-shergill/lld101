package snakesandladders_v1.playerFactory;

import snakesandladders_v1.Utils.Cell;
import snakesandladders_v1.playerFactory.playerTypes.AI;
import snakesandladders_v1.playerFactory.playerTypes.Human;

public class PlayerFactory {
    public static Player getPlayer(PlayerType playerType, String name, Cell position) {
        // Input validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        if (playerType == null) {
            throw new IllegalArgumentException("Player type cannot be null");
        }
        
        switch (playerType) {
            case HUMAN -> {
                return new Human(name, position);
            }
            case AI -> {
                return new AI(name, position);
            }
            default -> {
                throw new IllegalArgumentException("Unknown player type: " + playerType);
            }
        }
    }
    
    // Overloaded method for backward compatibility - provides default starting position
    public static Player getPlayer(PlayerType playerType, String name) {
        // Default starting position at position 1
        Cell defaultPosition = new Cell(0, 0, 1); // This will be updated when board is available
        return getPlayer(playerType, name, defaultPosition);
    }
}
