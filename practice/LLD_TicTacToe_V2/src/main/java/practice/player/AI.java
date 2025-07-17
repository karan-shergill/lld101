package practice.player;

import practice.constants.PlayingSymbol;
import practice.models.Board;
import practice.models.Cell;
import practice.models.Player;

public class AI extends Player {
    public AI(String name, PlayingSymbol playingSymbol) {
        super(name, playingSymbol);
    }

    public Cell playMove(Board board) {
        // scan board and whichever cell is empty return that
        return new Cell(0, 0);
    }
}
