package lld_vendingmachine_v1.managers;

import lld_vendingmachine_v1.constants.Coin;
import lld_vendingmachine_v1.paymentStrategy.PaymentStrategy;
import lld_vendingmachine_v1.paymentStrategy.CashPaymentStrategy;

import java.util.ArrayList;
import java.util.List;

// Manages payment-related operations including coin handling and change calculation
public class PaymentManager {
    private List<Coin> insertedCoins;
    private PaymentStrategy paymentStrategy;

    public PaymentManager() {
        this.insertedCoins = new ArrayList<>();
        this.paymentStrategy = new CashPaymentStrategy(); // Default strategy
    }

    // Add a coin to the payment
    public void addCoin(Coin coin) {
        insertedCoins.add(coin);
        System.out.println("Inserted " + coin.name() + " worth " + coin.value);
    }

    // Calculate total amount from inserted coins
    public int calculateTotal() {
        int total = 0;
        for (Coin coin : insertedCoins) {
            total += coin.value;
        }
        return total;
    }

    // Check if enough money has been inserted for the item
    public boolean hasEnoughMoney(int itemPrice) {
        return calculateTotal() >= itemPrice;
    }

    // Process payment and return change coins
    public List<Coin> processPaymentAndGetChange(int itemPrice) {
        int totalPaid = calculateTotal();
        if (totalPaid < itemPrice) {
            throw new IllegalStateException("Insufficient funds");
        }
        
        int changeAmount = totalPaid - itemPrice;
        List<Coin> change = paymentStrategy.calculateChange(changeAmount);
        
        // Clear inserted coins after successful payment
        resetPayment();
        
        return change;
    }

    // Reset payment state
    public void resetPayment() {
        insertedCoins.clear();
    }

    // Check if any coins have been inserted
    public boolean hasCoins() {
        return !insertedCoins.isEmpty();
    }

    // Getters and setters
    public List<Coin> getInsertedCoins() {
        return insertedCoins;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
