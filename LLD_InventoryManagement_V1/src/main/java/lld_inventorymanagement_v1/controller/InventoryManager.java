package lld_inventorymanagement_v1.controller;

import lld_inventorymanagement_v1.inventoryObserver.InventoryObserver;
import lld_inventorymanagement_v1.model.Warehouse;
import lld_inventorymanagement_v1.product.Product;
import lld_inventorymanagement_v1.replenishmentStrategy.ReplenishmentStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager inventoryManagerInstance;

    private List<Warehouse> warehouses;
    private ReplenishmentStrategy replenishmentStrategy;

    private InventoryManager(ReplenishmentStrategy replenishmentStrategy) {
        this.warehouses = new ArrayList<>();
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // Static method to get the singleton instance with thread safety
    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy) {
        if (inventoryManagerInstance == null) {
            inventoryManagerInstance = new InventoryManager(replenishmentStrategy);
        }
        return inventoryManagerInstance;
    }

    // Strategy pattern method
    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // Warehouse management
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        System.out.println("Warehouse added: " + warehouse.getName() + " at " + warehouse.getLocation());
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
        System.out.println("Warehouse removed: " + warehouse.getName());
    }
    
    // Add observer to all warehouses
    public void addObserverToAllWarehouses(InventoryObserver observer) {
        for (Warehouse warehouse : warehouses) {
            warehouse.addObserver(observer);
        }
    }
    
    // Add observer to specific warehouse
    public void addObserverToWarehouse(String warehouseName, InventoryObserver observer) {
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getName().equals(warehouseName)) {
                warehouse.addObserver(observer);
                break;
            }
        }
    }

    // Product inventory operations
    public Product getProductBySku(String sku) {
        for (Warehouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if (product != null) {
                return product;
            }
        }
        return null;
    }

    // Check stock levels and apply replenishment strategy if needed
    public void checkAndReplenish(String sku) {
        Product product = getProductBySku(sku);
        if (product != null) {
            // If product is below threshold
            if (product.getQuantity() < product.getThreshold()) {
                // Apply current replenishment strategy
                if (replenishmentStrategy != null) {
                    replenishmentStrategy.replenishment(product);
                }
            }
        }
    }

    // Cross-warehouse product search
    public List<ProductLocation> searchProductAcrossWarehouses(String sku) {
        List<ProductLocation> locations = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if (product != null) {
                locations.add(new ProductLocation(warehouse, product));
            }
        }
        return locations;
    }
    
    // Get total quantity across all warehouses
    public int getTotalQuantityAcrossWarehouses(String sku) {
        int totalQuantity = 0;
        for (Warehouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if (product != null) {
                totalQuantity += product.getQuantity();
            }
        }
        return totalQuantity;
    }
    
    // Generate inventory report across all warehouses
    public Map<String, Integer> generateInventoryReport() {
        Map<String, Integer> report = new HashMap<>();
        for (Warehouse warehouse : warehouses) {
            for (Product product : warehouse.getAllProducts()) {
                String sku = product.getSku();
                report.put(sku, report.getOrDefault(sku, 0) + product.getQuantity());
            }
        }
        return report;
    }
    
    // Real-time monitoring - check all products across all warehouses
    public void performRealTimeInventoryCheck() {
        System.out.println("\n=== Real-Time Inventory Check ===");
        int totalProducts = 0;
        int lowStockProducts = 0;
        
        for (Warehouse warehouse : warehouses) {
            System.out.println("\nChecking warehouse: " + warehouse.getName());
            for (Product product : warehouse.getAllProducts()) {
                totalProducts++;
                if (product.getQuantity() < product.getThreshold()) {
                    lowStockProducts++;
                    System.out.println("⚠️  LOW STOCK: " + product.getName() + 
                                     " (Current: " + product.getQuantity() + 
                                     ", Threshold: " + product.getThreshold() + ")");
                    
                    // Apply replenishment strategy
                    if (replenishmentStrategy != null) {
                        replenishmentStrategy.replenishment(product);
                    }
                } else {
                    System.out.println("✅ OK: " + product.getName() + 
                                     " (Current: " + product.getQuantity() + 
                                     ", Threshold: " + product.getThreshold() + ")");
                }
            }
        }
        
        System.out.println("\n=== Inventory Summary ===");
        System.out.println("Total products monitored: " + totalProducts);
        System.out.println("Products with low stock: " + lowStockProducts);
        System.out.println("=========================\n");
    }

    // Global inventory check (original method)
    public void performInventoryCheck() {
        for (Warehouse warehouse : warehouses) {
            for (Product product : warehouse.getAllProducts()) {
                if (product.getQuantity() < product.getThreshold()) {
                    if (replenishmentStrategy != null)  replenishmentStrategy.replenishment(product);
                }
            }
        }
    }
    
    // Get all warehouses
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }
    
    // Inner class to represent product location
    public static class ProductLocation {
        private Warehouse warehouse;
        private Product product;
        
        public ProductLocation(Warehouse warehouse, Product product) {
            this.warehouse = warehouse;
            this.product = product;
        }
        
        public Warehouse getWarehouse() { return warehouse; }
        public Product getProduct() { return product; }
        
        @Override
        public String toString() {
            return "Product: " + product.getName() + 
                   " | Warehouse: " + warehouse.getName() + 
                   " | Quantity: " + product.getQuantity();
        }
    }
}
