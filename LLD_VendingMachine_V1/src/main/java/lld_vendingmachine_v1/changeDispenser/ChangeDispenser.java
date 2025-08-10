package lld_vendingmachine_v1.changeDispenser;

import lld_vendingmachine_v1.constants.Coin;

import java.util.*;

// Handles change dispensing using greedy algorithm
public class ChangeDispenser {
    private Map<Coin, Integer> coinInventory;

    public ChangeDispenser() {
        initializeCoinInventory();
    }

    // Initialize the coin inventory with some coins
    private void initializeCoinInventory() {
        coinInventory = new HashMap<>();
        coinInventory.put(Coin.TEN_RUPEES, 20);
        coinInventory.put(Coin.FIVE_RUPEES, 30);
        coinInventory.put(Coin.TWO_RUPEES, 25);
        coinInventory.put(Coin.ONE_RUPEE, 50);
    }

    // Dispense change using greedy algorithm (largest denomination first)
    public List<Coin> dispenseChange(int changeAmount) {
        List<Coin> change = new ArrayList<>();
        
        if (changeAmount == 0) {
            return change;
        }

        // Sort coins by value in descending order for greedy approach
        List<Coin> sortedCoins = Arrays.asList(Coin.TEN_RUPEES, Coin.FIVE_RUPEES, 
                                              Coin.TWO_RUPEES, Coin.ONE_RUPEE);

        for (Coin coin : sortedCoins) {
            while (changeAmount >= coin.value && coinInventory.get(coin) > 0) {
                change.add(coin);
                changeAmount -= coin.value;
                coinInventory.put(coin, coinInventory.get(coin) - 1);
            }
        }

        if (changeAmount > 0) {
            System.out.println("Warning: Cannot provide exact change. Remaining: " + changeAmount);
        }

        return change;
    }

    // Add coins to the inventory (for restocking)
    public void addCoins(Coin coin, int count) {
        coinInventory.put(coin, coinInventory.get(coin) + count);
    }

    // Check if exact change can be given
    public boolean canGiveExactChange(int amount) {
        // Simple check - in a real implementation, this would be more sophisticated
        return amount <= getTotalValue();
    }

    // Get total value of all coins in inventory
    private int getTotalValue() {
        int total = 0;
        for (Map.Entry<Coin, Integer> entry : coinInventory.entrySet()) {
            total += entry.getKey().value * entry.getValue();
        }
        return total;
    }

    // Get current coin inventory status
    public Map<Coin, Integer> getCoinInventory() {
        return new HashMap<>(coinInventory);
    }
}
