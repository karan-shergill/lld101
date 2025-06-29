package tictactoev1.model;

public class PlayingPiece {
    private final PlayingPieceType playingPieceType;

    public  PlayingPiece(PlayingPieceType playingPieceType) {
        this.playingPieceType = playingPieceType;
    }

    public PlayingPieceType getPlayingPieceType() {
        return playingPieceType;
    }
}
