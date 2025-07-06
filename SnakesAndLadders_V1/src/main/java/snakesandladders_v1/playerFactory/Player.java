package snakesandladders_v1.playerFactory;

import snakesandladders_v1.Utils.Cell;
import snakesandladders_v1.Utils.Dice;

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
