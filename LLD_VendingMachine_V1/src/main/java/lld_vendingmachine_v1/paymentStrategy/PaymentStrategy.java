package lld_vendingmachine_v1.paymentStrategy;

import lld_vendingmachine_v1.constants.Coin;
import java.util.List;

// Strategy interface for different payment processing methods
public interface PaymentStrategy {
    
    // Process payment for the given amount
    boolean processPayment(int amount);
    
    // Calculate change and return as list of coins
    List<Coin> calculateChange(int changeAmount);
    
    // Validate if payment method can handle the amount
    boolean canProcess(int amount);
}
