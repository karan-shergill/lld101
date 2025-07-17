package practice.playerType;

import practice.modal.Board;
import practice.modal.Move;
import practice.modal.Player;

public class Human extends Player {
    public Human(String name, boolean whilePiece) {
        super(name, whilePiece);
    }

    @Override
    public boolean playMove(Move move, Board board) {
        // todo: complete method
        board.movePlayed(move);
        return true;
    }
}
