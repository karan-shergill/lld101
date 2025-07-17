package tictactoe.players;

import tictactoe.model.GameBoard;
import tictactoe.constants.PlayingPiece;
import tictactoe.model.Player;

public class HumanPlayer extends Player {
    public HumanPlayer(String playerName, PlayingPiece playerPlayingPiece) {
        super(playerName, playerPlayingPiece);
    }

    @Override
    public boolean makeMove(GameBoard gameBoard, int row, int col) {
        if (!gameBoard.isValidMove(row, col)) {
            return false;
        }
        gameBoard.getBoard()[row][col] = super.getPlayerPlayingPiece();
        return true;
    }
}
