package practice.models;

import practice.constants.PlayingSymbol;

public class Cell {
    private int row;
    private int col;
    private PlayingSymbol playingSymbol;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setPlayingSymbol(PlayingSymbol playingSymbol) {
        this.playingSymbol = playingSymbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public PlayingSymbol getPlayingSymbol() {
        return playingSymbol;
    }
}
