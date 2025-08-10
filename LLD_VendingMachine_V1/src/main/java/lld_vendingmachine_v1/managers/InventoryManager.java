package lld_vendingmachine_v1.managers;

import lld_vendingmachine_v1.modal.Inventory;
import lld_vendingmachine_v1.modal.Item;

// Manages inventory operations including adding, retrieving, and checking availability
public class InventoryManager {
    private Inventory inventory;

    public InventoryManager(int inventorySize) {
        this.inventory = new Inventory(inventorySize);
    }

    // Add an item to the inventory at specified code
    public void addItem(Item item, int code) {
        try {
            inventory.addItem(item, code);
            System.out.println("Added " + item.getItemType() + " to slot " + code);
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    // Get an item from inventory by code
    public Item getItem(int code) throws Exception {
        return inventory.getItem(code);
    }

    // Check if an item is available at the given code
    public boolean isAvailable(int code) {
        try {
            Item item = inventory.getItem(code);
            return item != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Remove an item from inventory
    public void removeItem(int code) throws Exception {
        inventory.removeItem(code);
        inventory.updateSoldOutItem(code);
    }

    // Check if inventory has any items
    public boolean hasItems() {
        return inventory.hasItems();
    }

    // Get the inventory instance (for backward compatibility)
    public Inventory getInventory() {
        return inventory;
    }
}
