package lld_inventorymanagement_v1.inventoryObserver;

import lld_inventorymanagement_v1.product.Product;

public class SupplierNotifier implements InventoryObserver{
    private String supplierName;
    private String contactEmail;

    public SupplierNotifier(String supplierName, String contactEmail) {
        this.supplierName = supplierName;
        this.contactEmail = contactEmail;
    }

    @Override
    public void update(Product product) {
        if (product.getQuantity() < product.getThreshold()) {
            // Send email notification to supplier
            System.out.println("Notification sent to " + supplierName
                    + " for low stock of " + product.getName());
        }
    }
}
