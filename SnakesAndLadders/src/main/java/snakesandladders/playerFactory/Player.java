package snakesandladders.playerFactory;

import snakesandladders.Utils.Cell;
import snakesandladders.Utils.Dice;

public abstract class Player {
    String name;
    Cell position;

    public Player(String name, Cell position) {
        this.name = name;
        this.position = position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Cell getPosition() {
        return position;
    }

    public int rollDice(Dice dice) {
        return dice.roll();
    }
}
