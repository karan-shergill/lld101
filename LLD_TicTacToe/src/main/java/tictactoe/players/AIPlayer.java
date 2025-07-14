package tictactoe.players;

import tictactoe.model.GameBoard;
import tictactoe.constants.PlayingPiece;
import tictactoe.model.Player;

public class AIPlayer extends Player {
    public AIPlayer(String playerName, PlayingPiece playerPlayingPiece) {
        super(playerName, playerPlayingPiece);
    }

    @Override
    public boolean makeMove(GameBoard gameBoard, int row, int col) {
        // For future extension
        // Simple AI: find first available move
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if (gameBoard.isValidMove(i, j)) {
                    gameBoard.getBoard()[i][j] = super.getPlayerPlayingPiece();
                    return true;
                }
            }
        }
        return false;
    }
}
