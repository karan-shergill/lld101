package practice.models;

import practice.constants.PlayingSymbol;

public abstract class Player {
    private String name;
    private PlayingSymbol playingSymbol;

    public Player(String name, PlayingSymbol playingSymbol) {
        this.name = name;
        this.playingSymbol = playingSymbol;
    }

    public String getName() {
        return name;
    }

    public PlayingSymbol getPlayingSymbol() {
        return playingSymbol;
    }
}
