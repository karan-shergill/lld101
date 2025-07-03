package tictactoev2.model.player;

import tictactoev2.model.GameBoard;
import tictactoev2.model.PlayingPiece;

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
