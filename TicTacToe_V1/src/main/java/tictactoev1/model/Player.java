package tictactoev1.model;

public class Player {
    private final String playerName;
    private final PlayingPiece playingPiece;

    public Player(String name, PlayingPiece piece) {
        this.playerName = name;
        this.playingPiece = piece;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }
}
