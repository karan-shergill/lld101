package Chess_V1.playerFactory;

public class PlayerFactory {
    public static Player getPlayerOfType(PlayerType playerType, String playerName, boolean isPlayerWhiteSide) {
        switch (playerType) {
            case HUMAN -> {
                return new HumanPlayer(playerName, isPlayerWhiteSide);
            }
            case AI -> {
                return new AIPlayer(playerName, isPlayerWhiteSide);
            }
        }
        return null;
    }
}
