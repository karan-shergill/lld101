package lld_inventorymanagement_v1.replenishmentStrategy.strategy;

import lld_inventorymanagement_v1.product.Product;
import lld_inventorymanagement_v1.replenishmentStrategy.ReplenishmentStrategy;

public class JustInTimeStrategy implements ReplenishmentStrategy {
    @Override
    public void replenishment(Product product) {
        // Implement Just-In-Time replenishment logic
        System.out.println("Applying Just-In-Time replenishment for " + product.getName());
        // Calculate optimal order quantity based on demand rate
    }
}
