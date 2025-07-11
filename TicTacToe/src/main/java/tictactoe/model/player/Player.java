package tictactoe.model.player;

import tictactoe.model.GameBoard;
import tictactoe.model.PlayingPiece;

public abstract class  Player {
    String playerName;
    PlayingPiece playerPlayingPiece;

    public Player(String playerName, PlayingPiece playerPlayingPiece) {
        this.playerName = playerName;
        this.playerPlayingPiece = playerPlayingPiece;
    }

    public abstract boolean makeMove(GameBoard gameBoard, int row, int col);

    public String getPlayerName() {
        return this.playerName;
    }

    public  PlayingPiece getPlayerPlayingPiece() {
        return this.playerPlayingPiece;
    }
}
