package lld_inventorymanagement_v1.inventoryObserver;

import lld_inventorymanagement_v1.product.Product;

public interface InventoryObserver {
    void update(Product product);
}
