package lld_inventorymanagement_v1.replenishmentStrategy.strategy;

import lld_inventorymanagement_v1.product.Product;
import lld_inventorymanagement_v1.replenishmentStrategy.ReplenishmentStrategy;

public class BulkOrderStrategy implements ReplenishmentStrategy {
    @Override
    public void replenishment(Product product) {
        // Implement Bulk Order replenishment logic
        System.out.println("Applying Bulk Order replenishment for " + product.getName());
        // Order in large quantities to minimize order costs
    }
}
