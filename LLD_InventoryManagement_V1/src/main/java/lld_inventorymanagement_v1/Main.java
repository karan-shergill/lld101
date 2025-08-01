package lld_inventorymanagement_v1;

import lld_inventorymanagement_v1.controller.InventoryManager;
import lld_inventorymanagement_v1.enums.ProductCategory;
import lld_inventorymanagement_v1.inventoryObserver.DashboardAlertSystem;
import lld_inventorymanagement_v1.inventoryObserver.SupplierNotifier;
import lld_inventorymanagement_v1.model.Warehouse;
import lld_inventorymanagement_v1.product.ProductDirector;
import lld_inventorymanagement_v1.product.products.Clothing;
import lld_inventorymanagement_v1.product.products.Electronics;
import lld_inventorymanagement_v1.product.products.Grocery;
import lld_inventorymanagement_v1.replenishmentStrategy.strategy.BulkOrderStrategy;
import lld_inventorymanagement_v1.replenishmentStrategy.strategy.JustInTimeStrategy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("🏭 INVENTORY MANAGEMENT SYSTEM DEMO");
        System.out.println("=================================\n");

        // Initialize the inventory manager with a replenishment strategy
        InventoryManager inventoryManager = InventoryManager.getInstance(new BulkOrderStrategy());
        ProductDirector productDirector = new ProductDirector();

        // Create warehouses
        System.out.println("📍 Creating Warehouses...");
        Warehouse warehouse1 = new Warehouse("Main Warehouse", "New York");
        Warehouse warehouse2 = new Warehouse("Secondary Warehouse", "Los Angeles");
        
        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);

        // Create observers
        System.out.println("\n🔔 Setting up Observers...");
        DashboardAlertSystem dashboardAlert = new DashboardAlertSystem("HIGH", 
                Arrays.asList("admin@company.com", "manager@company.com"));
        SupplierNotifier supplierNotifier = new SupplierNotifier("ABC Supplies", "supplier@abc.com");

        // Add observers to warehouses
        inventoryManager.addObserverToAllWarehouses(dashboardAlert);
        inventoryManager.addObserverToWarehouse("Main Warehouse", supplierNotifier);

        // Create products using Product Director (Builder + Director patterns)
        System.out.println("\n📦 Creating Products...");
        
        // Grocery products
        Grocery milk = productDirector.createGroceryProduct(
                "GR001", "Organic Milk", 4.99, 50, 15, 
                ProductCategory.GROCERY, new Date(), true);
        
        Grocery bread = productDirector.createGroceryProduct(
                "GR002", "Whole Wheat Bread", 2.49, 8, 10, 
                ProductCategory.GROCERY, new Date(), false);

        // Electronics products
        Electronics laptop = productDirector.createElectronicsProduct(
                "EL001", "Gaming Laptop", 1299.99, 25, 5, 
                ProductCategory.ELECTRONICS, 24, true);

        Electronics phone = productDirector.createElectronicsProduct(
                "EL002", "Smartphone", 699.99, 3, 5, 
                ProductCategory.ELECTRONICS, 12, true);

        // Clothing products
        Clothing tshirt = productDirector.createClothingProduct(
                "CL001", "Cotton T-Shirt", 19.99, 100, 20, 
                ProductCategory.CLOTHING, "L", "Cotton");

        // Add products to warehouses
        System.out.println("\n📥 Adding Products to Warehouses...");
        warehouse1.addProduct(milk, 50);
        warehouse1.addProduct(laptop, 25);
        warehouse1.addProduct(tshirt, 100);
        
        warehouse2.addProduct(bread, 8);  // This will trigger low stock alert
        warehouse2.addProduct(phone, 3);   // This will trigger low stock alert

        // Demonstrate cross-warehouse search
        System.out.println("\n🔍 Cross-Warehouse Product Search:");
        List<InventoryManager.ProductLocation> locations = inventoryManager.searchProductAcrossWarehouses("EL002");
        if (!locations.isEmpty()) {
            for (InventoryManager.ProductLocation location : locations) {
                System.out.println("Found: " + location);
            }
        }

        // Get total quantity across warehouses
        System.out.println("\n📊 Total Inventory Across Warehouses:");
        int totalMilk = inventoryManager.getTotalQuantityAcrossWarehouses("GR001");
        int totalPhones = inventoryManager.getTotalQuantityAcrossWarehouses("EL002");
        System.out.println("Total Milk quantity: " + totalMilk);
        System.out.println("Total Phone quantity: " + totalPhones);

        // Generate inventory report
        System.out.println("\n📋 Inventory Report:");
        Map<String, Integer> report = inventoryManager.generateInventoryReport();
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            System.out.println("SKU: " + entry.getKey() + " | Total Quantity: " + entry.getValue());
        }

        // Demonstrate real-time inventory monitoring
        inventoryManager.performRealTimeInventoryCheck();

        // Simulate inventory operations that trigger observers
        System.out.println("\n🔄 Simulating Inventory Operations...");
        System.out.println("Removing 40 units of milk from Main Warehouse:");
        warehouse1.removeProduct("GR001", 40);  // This should trigger alerts

        System.out.println("\nRemoving 20 units of laptops from Main Warehouse:");
        warehouse1.removeProduct("EL001", 20);  // This should trigger alerts

        // Change replenishment strategy
        System.out.println("\n🔧 Changing Replenishment Strategy to Just-In-Time:");
        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());

        // Perform another inventory check with new strategy
        System.out.println("\n🔄 Final Inventory Check with New Strategy:");
        inventoryManager.performRealTimeInventoryCheck();

        // Demonstrate adding more products after operations
        System.out.println("\n📈 Adding more inventory...");
        warehouse1.addProduct(milk, 30);  // Restocking
        
        System.out.println("\n✅ Demo Complete! All design patterns and features demonstrated:");
        System.out.println("   ✓ Singleton Pattern (InventoryManager)");
        System.out.println("   ✓ Builder Pattern (Product creation)");
        System.out.println("   ✓ Director Pattern (ProductDirector)");
        System.out.println("   ✓ Observer Pattern (Inventory alerts)");
        System.out.println("   ✓ Strategy Pattern (Replenishment strategies)");
        System.out.println("   ✓ Real-time monitoring");
        System.out.println("   ✓ Cross-warehouse operations");
        System.out.println("   ✓ Automatic threshold alerts");
    }
}