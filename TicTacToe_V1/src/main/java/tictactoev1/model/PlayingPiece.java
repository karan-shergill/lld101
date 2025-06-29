package tictactoev1.model;

public class PlayingPiece {
    private PlayingPieceType playingPieceType;

    public  PlayingPiece(PlayingPieceType playingPieceType) {
        this.playingPieceType = playingPieceType;
    }

    public PlayingPieceType getPlayingPieceType() {
        return playingPieceType;
    }
}
