package lld_inventorymanagement_v1.replenishmentStrategy;

import lld_inventorymanagement_v1.product.Product;

// Interface for different replenishment strategies
public interface ReplenishmentStrategy {
    // Method to replenish stock for a given product
    void replenishment(Product product);
}
