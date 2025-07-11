package tictactoe.model.player;

import tictactoe.model.GameBoard;
import tictactoe.model.PlayingPiece;

public class HumanPlayer extends Player {
    public HumanPlayer(String playerName, PlayingPiece playerPlayingPiece) {
        super(playerName, playerPlayingPiece);
    }

    @Override
    public boolean makeMove(GameBoard gameBoard, int row, int col) {
        if (!gameBoard.isValidMove(row, col)) {
            return false;
        }
        gameBoard.getBoard()[row][col] = playerPlayingPiece;
        return true;
    }
}
