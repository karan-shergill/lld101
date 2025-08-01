package lld_inventorymanagement_v1.model;

import lld_inventorymanagement_v1.inventoryObserver.InventoryObserver;
import lld_inventorymanagement_v1.inventoryObserver.InventorySubject;
import lld_inventorymanagement_v1.product.Product;

import java.util.*;

public class Warehouse implements InventorySubject {
    private UUID id;
    private String name;
    private String location;
    private Map<String, Product> products; // sku:product
    private List<InventoryObserver> observers; // Observer list

    public Warehouse(String name, String location) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.products = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    // Add a product to the warehouse
    public void addProduct(Product product, int quantity) {
        String sku = product.getSku();
        Product productToCheck;
        
        if (products.containsKey(sku)) {
            Product existingProduct = products.get(sku);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
            productToCheck = existingProduct;
        } else {
            // New product, add to inventory
            product.setQuantity(quantity);
            products.put(product.getSku(), product);
            productToCheck = product;
        }
        
        // Check threshold and notify observers if needed
        if (productToCheck.getQuantity() <= productToCheck.getThreshold()) {
            notifyObservers(productToCheck);
        }
    }

    // Remove a product from the warehouse
    public boolean removeProduct(String sku, int quantity) {
        if (products.containsKey(sku)) {
            Product product = products.get(sku);
            int currentQuantity = product.getQuantity();
            if (currentQuantity >= quantity) {
                // Sufficient inventory to remove
                product.setQuantity(currentQuantity - quantity);

                // If quantity becomes zero, consider removing the product entirely
                if (product.getQuantity() == 0) {
                    // Remove products with zero quantity
                    products.remove(sku);
                }
                
                // Check threshold and notify observers if needed
                if (product.getQuantity() <= product.getThreshold()) {
                    notifyObservers(product);
                }
                
                return true;
            } else {
                System.out.println("Error: Insufficient inventory. Requested: " + quantity + ", Available: " + currentQuantity);
                return false;
            }
        } else {
            System.out.println("Error: Product with SKU " + sku + " not found in " + name);
            return false;
        }
    }

    // Get available quantity of a product
    public int getAvailableQuantity(String sku) {
        if (products.containsKey(sku)) {
            return products.get(sku).getQuantity();
        }
        return 0; // Product not found
    }

    // Get a product by SKU
    public Product getProductBySku(String sku) {
        return products.get(sku);
    }

    // Get all products in this warehouse
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    // Observer pattern implementation
    @Override
    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
        System.out.println("Observer added to warehouse: " + name);
    }

    @Override
    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
        System.out.println("Observer removed from warehouse: " + name);
    }

    @Override
    public void notifyObservers(Product product) {
        System.out.println("Notifying " + observers.size() + " observers for product: " + product.getName());
        for (InventoryObserver observer : observers) {
            observer.update(product);
        }
    }

    // Getters for warehouse information
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
