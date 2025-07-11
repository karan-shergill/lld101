package snakesandladders.Utils;

import java.util.Random;

public class Dice {
    int noOfDice;
    int MIN = 1;
    int MAX = 6;

    public Dice(int noOfDice) {
        this.noOfDice = noOfDice;
    }

    public int roll() {
        int sum = 0;
        int diceUsed = 0;

        Random random = new Random();
        while (diceUsed < this.noOfDice) {
            sum += random.nextInt(MIN, MAX+1);
            diceUsed++;
        }
        return sum;
    }
}
