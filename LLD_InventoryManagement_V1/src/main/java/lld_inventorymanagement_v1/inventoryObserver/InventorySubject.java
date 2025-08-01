package lld_inventorymanagement_v1.inventoryObserver;

import lld_inventorymanagement_v1.product.Product;

public interface InventorySubject {
    void addObserver(InventoryObserver observer);
    void removeObserver(InventoryObserver observer);
    void notifyObservers(Product product);
}