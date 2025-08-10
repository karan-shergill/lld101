package lld_vendingmachine_v1.managers;

import lld_vendingmachine_v1.constants.Coin;
import lld_vendingmachine_v1.modal.Item;

import java.util.List;

// Manages complete transaction flow from selection to dispensing
public class TransactionManager {
    
    public TransactionManager() {
    }

    // Process a complete transaction
    public boolean processTransaction(int itemCode, PaymentManager paymentManager, 
                                    InventoryManager inventoryManager) {
        try {
            // Check if item is available
            if (!inventoryManager.isAvailable(itemCode)) {
                System.out.println("Item not available");
                return false;
            }

            // Get item details
            Item item = inventoryManager.getItem(itemCode);
            
            // Check if enough money has been inserted
            if (!paymentManager.hasEnoughMoney(item.getPrice())) {
                System.out.println("Insufficient funds. Item price: " + item.getPrice() 
                    + ", paid: " + paymentManager.calculateTotal());
                return false;
            }

            // Process payment and get change
            List<Coin> change = paymentManager.processPaymentAndGetChange(item.getPrice());
            
            // Dispense the item
            System.out.println("Dispensing: " + item.getItemType());
            inventoryManager.removeItem(itemCode);
            
            // Dispense change if any
            if (!change.isEmpty()) {
                System.out.print("Dispensing change: ");
                for (Coin coin : change) {
                    System.out.print(coin.name() + "(" + coin.value + ") ");
                }
                System.out.println();
            }

            System.out.println("Transaction completed successfully!");
            return true;

        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
            return false;
        }
    }

    // Cancel a transaction and return money
    public void cancelTransaction(PaymentManager paymentManager) {
        List<Coin> insertedCoins = paymentManager.getInsertedCoins();
        if (!insertedCoins.isEmpty()) {
            System.out.print("Returning coins: ");
            for (Coin coin : insertedCoins) {
                System.out.print(coin.name() + "(" + coin.value + ") ");
            }
            System.out.println();
        }
        paymentManager.resetPayment();
    }
}
